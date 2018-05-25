package com.mazelin.demo.elastic.model;

import com.mazelin.demo.elastic.repository.MandateRepository;
import org.elasticsearch.common.inject.Inject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MandateServiceImpl implements MandateService {

    private final MandateRepository repository;

    @Inject
    public MandateServiceImpl(MandateRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Mandate mandate) {
        repository.save(mandate);
    }

    @Override
    public Page<Mandate> findById(String id, Pageable pageable) {
        return repository.findById(id, pageable);
    }

    @Override
    public Page<Mandate> findMultiMatch(String query, Pageable page) {
        return repository.findMultiMatch(query,page);
    }
}
