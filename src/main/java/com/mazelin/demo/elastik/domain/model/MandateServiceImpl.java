package com.mazelin.demo.elastik.domain.model;

import com.mazelin.demo.elastik.repository.MandateRepository;
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
}
