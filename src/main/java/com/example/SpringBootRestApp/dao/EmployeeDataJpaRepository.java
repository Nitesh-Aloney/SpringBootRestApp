package com.example.SpringBootRestApp.dao;

import com.example.SpringBootRestApp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDataJpaRepository extends JpaRepository<Employee, Integer> {
}
