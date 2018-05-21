package com.mazelin.demo.elastic.batch.job.indexer;

import com.mazelin.demo.elastic.domain.repository.MandateRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
public class MandateElasticWriter implements ItemWriter<MandateInput> {


    private final MandateRepository mandateRepository;

    @Autowired
    public MandateElasticWriter(MandateRepository mandateRepository) {
        this.mandateRepository = mandateRepository;
    }

    @Override
    public void write(List<? extends MandateInput> items) throws Exception {



    }
}
