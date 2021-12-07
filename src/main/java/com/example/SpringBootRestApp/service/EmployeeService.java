package com.example.SpringBootRestApp.service;

import com.example.SpringBootRestApp.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    List<Employee> findAll();
    void save(Employee employee);
    Employee findById(Integer id);
    void update(Employee employee);
    void deleteById(Integer id);
    void delete(Employee employee);

}
