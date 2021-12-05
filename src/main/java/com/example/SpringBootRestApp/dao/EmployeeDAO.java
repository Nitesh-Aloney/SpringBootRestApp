package com.example.SpringBootRestApp.dao;

import com.example.SpringBootRestApp.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> listEmployees();
    void addEmployee(Employee employee);
    Employee getEmployeeById(Integer id);
    void update(Employee employee);
    void deleteById(Integer id);
    void deleteByEmployee(Employee employee);
}
