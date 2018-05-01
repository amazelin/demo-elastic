package com.mazelin.demo.elastik.controller;

import com.mazelin.demo.elastik.domain.model.Client;
import com.mazelin.demo.elastik.domain.model.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class SearchController {

    private final ClientService clientService;

    public SearchController(ClientService clientService) {
        this.clientService = clientService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String search(Map<String, Object> model) {
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Page<Client> search(String firstname){

        final Page<Client> clients = clientService.findByFirstname(firstname, Pageable.unpaged());

        return clients;
    }
}
