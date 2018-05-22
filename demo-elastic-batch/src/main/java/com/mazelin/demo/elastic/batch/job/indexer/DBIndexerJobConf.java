package com.mazelin.demo.elastic.batch.job.indexer;


import com.mazelin.demo.elastic.AppCoreConfig;
import com.mazelin.demo.elastic.batch.DataSourceConfig;
import com.mazelin.demo.elastic.batch.JobConfig;
import com.mazelin.demo.elastic.model.Client;
import com.mazelin.demo.elastic.model.Mandate;
import com.mazelin.demo.elastic.repository.MandateRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
@EnableBatchProcessing
@Import({DataSourceConfig.class, JobConfig.class, AppCoreConfig.class})
public class DBIndexerJobConf {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job indexJob(Step step1)  {
        return jobBuilderFactory.get("dbIndexJob").start(step1).build();
    }

    @Bean
    public MandateElasticWriter mandateElasticWriter(MandateRepository mandateRepository) {
        return new MandateElasticWriter(mandateRepository);
    }

    @Bean
    public Step step1(ItemReader<Mandate> reader, ItemWriter<Mandate> writer) {
        return stepBuilderFactory.get("reader")
            .<Mandate, Mandate> chunk(1000)
            .reader(reader)
            .writer(writer)
            .build();
    }

    @Bean
    public ItemReader<Mandate> dbReader(DataSource dataSource, PagingQueryProvider queryProvider, RowMapper<Mandate> rowMapper) {
        return new JdbcPagingItemReaderBuilder<Mandate>()
            .dataSource(dataSource)
            .queryProvider(queryProvider)
            .rowMapper(rowMapper)
            .pageSize(1000)
            .saveState(false)
            .build();
    }

    @Bean
    public RowMapper<Mandate> rowMapper() {
        return (rs, rowNum) -> {
            Mandate mandate = new Mandate();
            mandate.setId(rs.getString("MDT_NUM"));
            mandate.setOffer(rs.getString("OFR_CODE"));
            mandate.setStructure(rs.getString("STRUCT"));
            mandate.setRiskProfile(rs.getString("RISK"));
            Client client = new Client();
            client.setFirstname(rs.getString("FIRSTNAME"));
            client.setLastname(rs.getString("LASTNAME"));
            mandate.setClient(client);

            return mandate;
        };
    }

    @Bean
    public PagingQueryProvider queryProvider(DataSource dataSource) throws Exception {
        final SqlPagingQueryProviderFactoryBean queryProviderFactory = new SqlPagingQueryProviderFactoryBean();
        queryProviderFactory.setDataSource(dataSource);
        queryProviderFactory.setSelectClause("mdt.MDT_ID as MDT_ID, mdt.MDT_NUM as MDT_NUM, ofr.OFR_CODE as OFR_CODE, struc.LABEL as STRUCT, risk.LIBELLE as RISK, tit.NOM as LASTNAME, tit.PRENOM as FIRSTNAME");
        queryProviderFactory.setFromClause("from MDT_MANDAT mdt " +
                "inner join MGS_COMMON.OFR_OFFERTYPE ofr on ofr.OFR_ID=mdt.OFR_ID " +
                "inner join MGS_COMMON.TYPE struc on struc.TYP_ID=mdt.STRUCT_ID " +
                "inner join MGS_COMMON.MDT_PROFILS_RISQUES risk on risk.RISQ_ID=mdt.RISQ_ID " +
                "inner join MGS_ADMIN.TITULAIRES tit on mdt.TITU_PRINC_ID=tit.TITULAIRE_ID");
        queryProviderFactory.setWhereClause("where mdt.MDT_ID > 1000");
        queryProviderFactory.setSortKey("MDT_ID");

        return queryProviderFactory.getObject();
    }

}
