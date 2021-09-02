package com.example.companyemployee.controller;

import com.example.companyemployee.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/registration/employee")
    public String registrationEmployee() {
        return "registrEmployee";
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal CurrentUser currentUser){
        if (currentUser != null){
            return "redirect:/employeeHome";
        }
        return "index";
    }
}
