package com.example.alishev_rest.controller;

import com.example.alishev_rest.model.Employee;
import com.example.alishev_rest.repository.PeopleRepository;
import com.example.alishev_rest.services.PeopleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private final PeopleServise peopleService;

    public PeopleController(PeopleServise peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("")
    public List<Employee> getAll() {
        return peopleService.findAll();
    }
@GetMapping("/{id}")
    public Employee findOne(@PathVariable("id") int id) {
        return peopleService.findOne(id);
    }
}

