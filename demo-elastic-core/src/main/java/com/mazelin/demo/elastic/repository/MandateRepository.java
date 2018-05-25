package com.mazelin.demo.elastic.repository;

import com.mazelin.demo.elastic.model.Mandate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MandateRepository extends ElasticsearchRepository<Mandate, String>{

    Page<Mandate> findById(String id, Pageable pageable);

    @Query("{\"multi_match\": {\"query\": \"?0\" ,\"fields\" : [\"id\", \"offer\", \"structure\", \"riskProfile\", \"client.firstname\", \"client.lastname\"]}}")
    Page<Mandate> findMultiMatch(String query, Pageable pageable);
}
