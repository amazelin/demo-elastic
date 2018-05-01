package com.mazelin.demo.elastik.repository;

import com.mazelin.demo.elastik.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ClientRepository extends ElasticsearchRepository<Client, String> {

    Page<Client> findByFirstname(String firstname, Pageable pageable);


    Page<Client> findByLastname(String lastname, Pageable pageable);
}
