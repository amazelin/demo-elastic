package com.mazelin.demo.elastic.batch.job.indexer;

import com.mazelin.demo.elastic.AppCoreConfig;
import com.mazelin.demo.elastic.model.Client;
import com.mazelin.demo.elastic.model.Mandate;
import com.mazelin.demo.elastic.repository.MandateRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@Import(AppCoreConfig.class)
public class FileIndexerJobConf {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job indexJob(Step step1) {
        return jobBuilderFactory.get("fileIndexJob").start(step1).build();
    }

    @Bean
    public MandateElasticWriter mandateElasticWriter(MandateRepository mandateRepository) {
        return new MandateElasticWriter(mandateRepository);
    }

    @Bean
    public Step step1(ItemReader<Mandate> reader, ItemWriter<Mandate> writer) {
        return stepBuilderFactory.get("reader")
            .<Mandate, Mandate>chunk(100)
            .reader(reader)
            .writer(writer)
            .build();
    }

    @Bean
    public ItemReader<Mandate> fileReader() {

        final DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(";");
        tokenizer.setNames("id", "offer", "structure", "riskProfile", "assets", "firstname", "lastname", "clientId");

        return new FlatFileItemReaderBuilder<Mandate>()
                .linesToSkip(1)
                .resource(new FileSystemResource("src/main/resources/data.csv"))
                .fieldSetMapper(fieldSet -> {
                    Mandate mandate = new Mandate();
                    mandate.setId(fieldSet.readString("id"));
                    mandate.setOffer(fieldSet.readString("offer"));
                    mandate.setRiskProfile(fieldSet.readString("riskProfile"));
                    mandate.setStructure(fieldSet.readString("structure"));
                    mandate.setAssets(fieldSet.readDouble("assets"));

                    Client client = new Client();
                    client.setId(fieldSet.readString("clientId"));
                    client.setFirstname(fieldSet.readString("firstname"));
                    client.setLastname(fieldSet.readString("lastname"));

                    mandate.setClient(client);
                    return mandate;
                })
                .lineTokenizer(tokenizer)
                .strict(true)
                .saveState(false)
                .build();
    }


}
