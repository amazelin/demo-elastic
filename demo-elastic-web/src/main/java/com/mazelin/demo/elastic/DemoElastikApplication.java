package com.mazelin.demo.elastic;

import com.mazelin.demo.elastic.model.Client;
import com.mazelin.demo.elastic.model.Mandate;
import com.mazelin.demo.elastic.model.MandateService;
import org.elasticsearch.common.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootApplication
public class DemoElastikApplication implements CommandLineRunner {


	private final ElasticsearchOperations elasticsearchOperations;
	private final MandateService mandateService;


	@Inject
	public DemoElastikApplication(@Qualifier("appElasticSearch") ElasticsearchOperations elasticsearchOperations, MandateService mandateService) {
		this.elasticsearchOperations = elasticsearchOperations;
		this.mandateService = mandateService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoElastikApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		elasticsearchOperations.createIndex("gp");

//		elasticsearchOperations.deleteIndex(Client.class);
//		elasticsearchOperations.deleteIndex(Mandate.class);


		/*elasticsearchOperations.refresh("gp");
		elasticsearchOperations.refresh(Mandate.class);

		final Client c1 = new Client("123", "Mazelin", "Sophie");
		final Client c2 = new Client("456", "Mazelin", "Arnaud");

		final Mandate m1 = new Mandate("ABC", "GAO", "CTO", "Mandat 0-30", 150000D, c1);
		final Mandate m2 = new Mandate("DEF", "GSM", "PEA", "Mandat 70-90", 1857000D, c2);

		mandateService.save(m1);
		mandateService.save(m2);*/
	}
}
