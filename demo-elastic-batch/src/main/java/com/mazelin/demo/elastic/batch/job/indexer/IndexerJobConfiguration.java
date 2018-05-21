package com.mazelin.demo.elastic.batch.job.indexer;


import com.mazelin.demo.elastic.batch.DataSourceConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.RowMapper;

@Configuration
@EnableBatchProcessing
@Import({DataSourceConfig.class})
public class IndexerJobConfiguration {


    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSourceConfig dataSourceConfig;

    @Autowired
    MandateElasticWriter mandateElasticWriter;


    @Bean
    public Job indexJob() throws Exception {
        return jobBuilderFactory.get("indexJob").start(step1()).build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("reader")
                .<MandateInput, MandateInput> chunk(100)
                .reader(dbReader())
                .writer(mandateElasticWriter)
                .build();
    }

    @Bean
    public ItemReader<MandateInput> dbReader() throws Exception {

        return new JdbcPagingItemReaderBuilder<MandateInput>()
                .dataSource(dataSourceConfig.dataSource())
                .queryProvider(queryProvider())
                .rowMapper(rowMapper())
                .build();
    }

    @Bean
    public RowMapper<MandateInput> rowMapper() {

        return null;
    }

    @Bean
    public PagingQueryProvider queryProvider() throws Exception {
        final SqlPagingQueryProviderFactoryBean queryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();
        queryProviderFactoryBean.setDataSource(dataSourceConfig.dataSource());
        queryProviderFactoryBean.setSelectClause("");
        queryProviderFactoryBean.setFromClause("");
        queryProviderFactoryBean.setWhereClause("");
        queryProviderFactoryBean.setSortKey("");


        return queryProviderFactoryBean.getObject();
    }




}
