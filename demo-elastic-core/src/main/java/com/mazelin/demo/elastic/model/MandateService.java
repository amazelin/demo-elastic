package com.mazelin.demo.elastic.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MandateService {

    void save(Mandate mandate);

    Page<Mandate> findById(String id, Pageable pageable);

    Page<Mandate> findMultiMatch(String query, Pageable page);
}
