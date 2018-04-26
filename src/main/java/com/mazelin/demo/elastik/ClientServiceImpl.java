package com.mazelin.demo.elastik;


import com.mazelin.demo.elastik.repository.ClientRepository;
import org.elasticsearch.common.inject.Inject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Inject
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Page<Client> findByFirstname(String firstname, Pageable pageable) {
        return clientRepository.findByFirstname(firstname, pageable);
    }

}
