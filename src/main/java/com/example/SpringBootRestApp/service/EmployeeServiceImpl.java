package com.example.SpringBootRestApp.service;

import com.example.SpringBootRestApp.dao.EmployeeDAO;
import com.example.SpringBootRestApp.entity.Employee;
import com.example.SpringBootRestApp.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    @Qualifier("employeeDAOImplJPA")
    private EmployeeDAO employeeAccessor;

    @Override
    public List<Employee> findAll() {
        //  collect the emplyees using DAO
        List<Employee> employees = employeeAccessor.findAll();

        //  Return the result
        return employees;

    }

    @Override
    public void save(Employee employee){
        employeeAccessor.save(employee);
    }

    @Override
    public Employee findById(Integer id) {
        Employee employee;

        try{
            employee = employeeAccessor.findById(id);
            return employee;
        }catch (Exception e){
            throw new EmployeeNotFoundException("Employee with id : "+id+" not found");
        }
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
