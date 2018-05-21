package com.mazelin.demo.elastic.controller;

import com.mazelin.demo.elastic.model.Mandate;
import com.mazelin.demo.elastic.model.MandateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class SearchController {

    private final MandateService mandateService;

    public SearchController(MandateService mandateService) {

        this.mandateService = mandateService;
    }


    @GetMapping(value = "/")
    public String search(Map<String, Object> model) {
        return "search";
    }

    @PostMapping(value = "/search")
    public @ResponseBody
    ResponseEntity<Page<Mandate>> search(@RequestBody SearchCriteria searchCriteria, Errors errors){

        final Page<Mandate> clients = mandateService.findById(searchCriteria.getQuery(), Pageable.unpaged());

        return ResponseEntity.ok(clients);
    }
}
