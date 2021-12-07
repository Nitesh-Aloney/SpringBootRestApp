package com.example.SpringBootRestApp.controller;

import com.example.SpringBootRestApp.entity.Employee;
import com.example.SpringBootRestApp.exception.EmployeeDataMissingException;
import com.example.SpringBootRestApp.exception.EmployeeNotFoundException;
import com.example.SpringBootRestApp.exception.response.EmployeeResponse;
import com.example.SpringBootRestApp.exception.response.Response;
import com.example.SpringBootRestApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    @Qualifier("employeeServiceImpl")
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<EmployeeResponse> listEmployee(){

        List<Employee> employees;

        try{
            employees = employeeService.findAll();
            EmployeeResponse response = new EmployeeResponse(
                    HttpStatus.OK.value(),
                    "SUCCESS",
                    System.currentTimeMillis(),
                    employees
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable("id") Integer id){
        Employee employee = employeeService.findById(id);

        EmployeeResponse response = new EmployeeResponse(
                HttpStatus.OK.value(),
                "SUCCESS",
                System.currentTimeMillis(),
                employee
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody  Employee employee){

        if(employee == null || employee.getEmail() == null ||
                employee.getFirstName() == null ||
                employee.getLastName() == null){
            throw new EmployeeDataMissingException("Given employee has data missing !!");
        }

        employeeService.save(employee);

        EmployeeResponse response = new EmployeeResponse(
                HttpStatus.OK.value(),
                "SUCCESS",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody Employee employee){

        if(employee == null || employee.getId()==null ||
                employee.getEmail() == null ||
                employee.getFirstName() == null ||
                employee.getLastName() == null){
            throw new EmployeeDataMissingException("Given employee has data missing !!");
        }

        employeeService.update(employee);

        EmployeeResponse response = new EmployeeResponse(
                HttpStatus.OK.value(),
                "SUCCESS",
                System.currentTimeMillis(),
                employee
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable Integer id){
        employeeService.deleteById(id);

        EmployeeResponse response = new EmployeeResponse(
                HttpStatus.OK.value(),
                "SUCCESS",
                System.currentTimeMillis(),
                ""
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@RequestBody Employee employee){

        if(employee == null || employee.getId()==null ||
                employee.getEmail() == null ||
                employee.getFirstName() == null ||
                employee.getLastName() == null){
            throw new EmployeeDataMissingException( "Given employee has data missing !!");
        }

        employeeService.delete(employee);

        EmployeeResponse response = new EmployeeResponse(
                HttpStatus.OK.value(),
                "SUCCESS",
                System.currentTimeMillis(),
                ""
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
