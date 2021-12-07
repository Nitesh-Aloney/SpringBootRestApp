package com.example.SpringBootRestApp.service;


import com.example.SpringBootRestApp.dao.EmployeeDAOImplJPA;
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
public class EmployeeServiceJpaRepoImpl implements EmployeeService{

    @Autowired
    @Qualifier("employeeDataJpaRepository")
    private EmployeeDataJpaRepository employeeAccessor;

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeAccessor.findAll();

        return employees;
    }

    @Override
    public void save(Employee employee){
        employeeAccessor.save(employee);
    }

    @Override
    public Employee findById(Integer id) {
        Optional<Employee> result = employeeAccessor.findById(id);

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
            findById(employee.getId());
        }catch (EmployeeNotFoundException e){
            throw  new EmployeeNotFoundException(employee+" doesn't not exist to update information");
        }
        employeeAccessor.save(employee);
    }

    @Override
    public void deleteById(Integer id) {
        try{
            employeeAccessor.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EmployeeNotFoundException("Employee with id : "+id+" not found");
        }
    }

    @Override
    public void delete(Employee employee) {
        try{
            employeeAccessor.delete(employee);
        }catch (EmptyResultDataAccessException e){
            throw new EmployeeNotFoundException(employee+" not found");
        }
    }

}
