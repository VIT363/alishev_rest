package com.example.alishev_rest.controller;

import com.example.alishev_rest.model.Employee;
import com.example.alishev_rest.repository.PeopleRepository;
import com.example.alishev_rest.services.PeopleServise;
import com.example.alishev_rest.util.PersonErrorResponse;
import com.example.alishev_rest.util.PersonNotCreatedException;
import com.example.alishev_rest.util.PersonNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.lang.reflect.Field;
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

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Employee employee,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMessage.toString());
        }
        peopleService.save(employee);
        return  ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> HandleException(PersonNotCreatedException e) {
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                "Person with this id its not found",
                System.currentTimeMillis());

        return new ResponseEntity<>(personErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> HandleException(PersonNotFoundException e) {
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(personErrorResponse, HttpStatus.NOT_FOUND);
    }
}

