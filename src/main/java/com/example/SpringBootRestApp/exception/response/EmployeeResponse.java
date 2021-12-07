package com.example.SpringBootRestApp.exception.response;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.Inet4Address;

public class EmployeeResponse extends Response{

    private Object data;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Integer status, String message, Long timeStamp) {
        super(status, message, timeStamp);
    }

    public EmployeeResponse(Integer status, String message, Long timeStamp, Object data){
        this.data = data;
        this.setMessage(message);
        this.setStatus(status);
        this.setTimeStamp(timeStamp);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
