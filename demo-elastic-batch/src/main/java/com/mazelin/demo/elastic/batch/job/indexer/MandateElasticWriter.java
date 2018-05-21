package com.mazelin.demo.elastic.batch.job.indexer;

import com.mazelin.demo.elastic.model.Mandate;
import com.mazelin.demo.elastic.repository.MandateRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MandateElasticWriter implements ItemWriter<Mandate> {


    private final MandateRepository mandateRepository;

    @Autowired
    public MandateElasticWriter(MandateRepository mandateRepository) {
        this.mandateRepository = mandateRepository;
    }

    @Override
    public void write(List<? extends Mandate> items) {
        mandateRepository.saveAll(items);
    }
}
