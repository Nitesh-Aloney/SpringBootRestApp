package com.example.SpringBootRestApp.service;


import com.example.SpringBootRestApp.dao.EmployeeDAO;
import com.example.SpringBootRestApp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    @Qualifier("employeeDAOImplJPA")
    private EmployeeDAO employeeDAO;

    @Override
    public List<Employee> listEmployees() {

        //  collect the emplyees using DAO
        List<Employee> employees = employeeDAO.listEmployees();

        //  Return the result
        return employees;

    }

    @Override
    public void addEmployee(Employee employee){
        employeeDAO.addEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeDAO.getEmployeeById(id);
    }

    @Override
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void deleteById(Integer id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public void deleteByEmployee(Employee employee) {
        employeeDAO.deleteByEmployee(employee);
    }

}
