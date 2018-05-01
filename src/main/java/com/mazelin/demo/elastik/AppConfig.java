package com.mazelin.demo.elastik;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories
public class AppConfig {


    @Bean(name = "appElasticSearch")
    public ElasticsearchOperations elasticSearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }

    @Bean
    public Client client() throws UnknownHostException {

        final Settings settings = Settings.builder().put("cluster.name", "gp-cluster").build();


        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

        return client;
    }



}
