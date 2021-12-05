package com.example.SpringBootRestApp.controller;

import com.example.SpringBootRestApp.entity.Employee;
import com.example.SpringBootRestApp.exception.EmployeeDataMissingException;
import com.example.SpringBootRestApp.exception.EmployeeNotFoundException;
import com.example.SpringBootRestApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> listEmployee(){
        return employeeService.listEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/")
    public void addEmployee(@RequestBody  Employee employee){

        if(employee == null || employee.getId()==null ||
                employee.getEmail() == null ||
                employee.getFirstName() == null ||
                employee.getLastName() == null){
            throw new EmployeeNotFoundException("Given employee has data missing !!");
        }

        employeeService.addEmployee(employee);
    }

    @PutMapping("/")
    public void updateEmployee(@RequestBody Employee employee){

        if(employee == null || employee.getId()==null ||
                employee.getEmail() == null ||
                employee.getFirstName() == null ||
                employee.getLastName() == null){
            throw new EmployeeNotFoundException("Given employee has data missing !!");
        }

        employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        employeeService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteEmployee(@RequestBody Employee employee){

        if(employee == null || employee.getId()==null ||
                employee.getEmail() == null ||
                employee.getFirstName() == null ||
                employee.getLastName() == null){
            throw new EmployeeDataMissingException( "Given employee has data missing !!");
        }

        employeeService.deleteByEmployee(employee);
    }

}
