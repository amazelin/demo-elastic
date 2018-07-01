package com.mazelin.demo.elastic.repository;

import com.mazelin.demo.elastic.model.Mandate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Repository
public interface MandateCustomRepository {

    List<Mandate> findAll(Set<String> offers, String query) throws IOException;
}
