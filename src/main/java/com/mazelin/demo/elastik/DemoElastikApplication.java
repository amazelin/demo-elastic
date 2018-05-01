package com.mazelin.demo.elastik;

import com.mazelin.demo.elastik.domain.model.Client;
import com.mazelin.demo.elastik.domain.model.ClientService;
import org.elasticsearch.common.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootApplication
public class DemoElastikApplication implements CommandLineRunner {


	private final ElasticsearchOperations elasticsearchOperations;
	private final ClientService clientService;


	@Inject
	public DemoElastikApplication(@Qualifier("appElasticSearch") ElasticsearchOperations elasticsearchOperations, ClientService clientService) {
		this.elasticsearchOperations = elasticsearchOperations;
		this.clientService = clientService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoElastikApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		elasticsearchOperations.deleteIndex(Client.class);
		clientService.save(new Client("123", "Mazelin", "Sophie"));
		clientService.save(new Client("456", "Mazelin", "Arnaud"));

		clientService.findByFirstname("Arnaud", Pageable.unpaged()).forEach(System.out::println);
		clientService.findByLastname("Mazelin", Pageable.unpaged()).forEach(System.out::println);

	}
}
