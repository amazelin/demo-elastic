package com.mazelin.demo.elastik;

import org.elasticsearch.common.inject.Inject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.function.Consumer;

@SpringBootApplication
public class DemoElastikApplication implements CommandLineRunner {


	private final ClientService clientService;


	@Inject
	public DemoElastikApplication(ClientService clientService) {
		this.clientService = clientService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoElastikApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		clientService.save(new Client("123", "Mazelin", "Arnaud"));

		clientService.findByFirstname("Arnaud", PageRequest.of(1, 10)).forEach(client -> System.out.print(client.toString()));

	}
}
