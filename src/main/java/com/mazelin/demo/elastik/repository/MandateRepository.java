package com.mazelin.demo.elastik.repository;

import com.mazelin.demo.elastik.domain.model.Mandate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MandateRepository extends ElasticsearchRepository<Mandate, String>{

    Page<Mandate> findById(String id, Pageable pageable);
}
