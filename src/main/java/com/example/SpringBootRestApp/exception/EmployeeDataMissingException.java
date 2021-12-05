package com.example.SpringBootRestApp.exception;

public class EmployeeDataMissingException extends EmployeeDefaultException{
    public EmployeeDataMissingException(String message) {
        super(message);
    }

    public EmployeeDataMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeDataMissingException(Throwable cause) {
        super(cause);
    }
}
