package com.example.SpringBootRestApp.dao;

import com.example.SpringBootRestApp.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    void save(Employee employee);
    Employee findById(Integer id);
    void update(Employee employee);
    void deleteById(Integer id);
    void delete(Employee employee);
}
