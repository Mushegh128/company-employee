package com.example.companyemployee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/registrationCompany")
    public String regCompany(){
        return "registrationCompany";
    }

    @GetMapping("/registrationEmployee")
    public String regEmployee(){
        return "registrationEmployee";
    }


}
