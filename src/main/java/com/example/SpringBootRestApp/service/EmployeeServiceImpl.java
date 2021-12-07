package com.example.SpringBootRestApp.service;


import com.example.SpringBootRestApp.dao.EmployeeDataJpaRepository;
import com.example.SpringBootRestApp.entity.Employee;
import com.example.SpringBootRestApp.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    @Qualifier("employeeDataJpaRepository")
    private EmployeeDataJpaRepository employeeRepository;

    @Override
    public List<Employee> listEmployees() {

        //  collect the emplyees using DAO
        List<Employee> employees = employeeRepository.findAll();

        //  Return the result
        return employees;

    }

    @Override
    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;

        if(result.isPresent()){
            employee = result.get();
        }else{
            throw new EmployeeNotFoundException("Employee with id : "+id+" not found");
        }

        return employee;
    }

    @Override
    public void update(Employee employee) {
        try {
            getEmployeeById(employee.getId());
        }catch (EmployeeNotFoundException e){
            throw  new EmployeeNotFoundException(employee+" doesn't not exist to update information");
        }
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Integer id) {
        try{
            employeeRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EmployeeNotFoundException("Employee with id : "+id+" not found");
        }
    }

    @Override
    public void deleteByEmployee(Employee employee) {
        try{
            employeeRepository.delete(employee);
        }catch (EmptyResultDataAccessException e){
            throw new EmployeeNotFoundException(employee+" not found");
        }
    }

}
