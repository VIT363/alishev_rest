package com.example.alishev_rest.services;

import com.example.alishev_rest.repository.PeopleRepository;
import com.example.alishev_rest.model.Employee;
import com.example.alishev_rest.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleServise {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleServise(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Employee> findAll() {
        return peopleRepository.findAll();
    }
    public Employee findOne(int id) {
        Optional <Employee> employee = peopleRepository.findById(id);
        return employee.orElseThrow(PersonNotFoundException::new);
    }
    @Transactional
    public void save(Employee employee) {
        peopleRepository.save(employee);
    }

}
