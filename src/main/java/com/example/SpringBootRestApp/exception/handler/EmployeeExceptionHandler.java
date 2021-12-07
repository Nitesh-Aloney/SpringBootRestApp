package com.example.SpringBootRestApp.exception.handler;


import com.example.SpringBootRestApp.exception.EmployeeDataMissingException;
import com.example.SpringBootRestApp.exception.EmployeeNotFoundException;
import com.example.SpringBootRestApp.exception.response.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class EmployeeExceptionHandler {
    Logger log = Logger.getLogger(getClass().getName());

    @ExceptionHandler
    public ResponseEntity<EmployeeResponse> notFoundHandler(EmployeeNotFoundException e){
        log.warning(e.getMessage());

        EmployeeResponse errorResponse = new EmployeeResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeResponse> dataMissingHandler(EmployeeDataMissingException e){
        log.warning(e.getMessage());

        EmployeeResponse errorResponse = new EmployeeResponse(
                HttpStatus.PARTIAL_CONTENT.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.PARTIAL_CONTENT);
    }
}
