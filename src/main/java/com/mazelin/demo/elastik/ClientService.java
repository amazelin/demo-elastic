package com.mazelin.demo.elastik;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Client save(Client client);

    Page<Client> findByFirstname(String firstname, Pageable pageable);

    Page<Client> findByLastname(String lastname, Pageable pageable);

}
