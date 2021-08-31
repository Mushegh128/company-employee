package com.example.companyemployee.controller;

import com.example.companyemployee.security.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/registration/employee")
    public String registrationEmployee() {
        return "registrEmployee";
    }
}
