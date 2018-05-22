package com.mazelin.demo.elastic.batch;

import org.springframework.beans.factory.config.FieldRetrievingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(DBResourceResolver dbResourceResolver) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        FieldRetrievingFactoryBean fieldRetrievingFactoryBean = new FieldRetrievingFactoryBean();
        fieldRetrievingFactoryBean.setStaticField(dbResourceResolver.getDBResourceStaticFieldName());
        fieldRetrievingFactoryBean.afterPropertiesSet();
        DataSource dataSource = (DataSource) fieldRetrievingFactoryBean.getObject();
        return dataSource;
    }

    @Bean
    public DBResourceResolver dbResourceResolverBatch() {
        return new DBResourceResolver("MGS_ADMIN");
    }
}
