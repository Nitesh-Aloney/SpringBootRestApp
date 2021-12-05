package com.example.SpringBootRestApp.exception;

public class EmployeeDefaultException extends RuntimeException{
    public EmployeeDefaultException(String message) {
        super(message);
    }

    public EmployeeDefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeDefaultException(Throwable cause) {
        super(cause);
    }
}
