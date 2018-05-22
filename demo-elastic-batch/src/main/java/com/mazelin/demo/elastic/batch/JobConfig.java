package com.mazelin.demo.elastic.batch;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    protected JobRepository jobRepository() throws Exception {
        MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
        //factory.setDataSource(dataSource);
        factory.setTransactionManager(new ResourcelessTransactionManager());
        factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        //factory.setTablePrefix("BATCH_");
        factory.afterPropertiesSet();
        //factory.setLobHandler(lobHandler);
        return factory.getObject();
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);

        return jobLauncher;
    }
}
