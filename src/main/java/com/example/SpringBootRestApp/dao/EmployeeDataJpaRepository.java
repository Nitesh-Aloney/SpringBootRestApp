package com.example.SpringBootRestApp.dao;

import com.example.SpringBootRestApp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "employee-api")
public interface EmployeeDataJpaRepository extends JpaRepository<Employee, Integer> {
}
