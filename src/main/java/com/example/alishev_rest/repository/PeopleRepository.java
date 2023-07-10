package com.example.alishev_rest.repository;

import com.example.alishev_rest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<Employee, Integer> {
}
