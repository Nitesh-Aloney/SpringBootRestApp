package com.example.SpringBootRestApp.service;

import com.example.SpringBootRestApp.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> listEmployees();
    void addEmployee(Employee employee);
    Employee getEmployeeById(Integer id);
    void update(Employee employee);
    void deleteById(Integer id);
    void deleteByEmployee(Employee employee);

}
