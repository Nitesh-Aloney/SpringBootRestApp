package com.example.SpringBootRestApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class IndexController {

    @GetMapping("/")
    public void showIndex(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8080/springbootrest/employee/");
    }
}
