package com.example.companyemployee.controller.advice;

import com.example.companyemployee.model.Employee;
import com.example.companyemployee.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MainAdvice {

    @ModelAttribute("currentUser")
    public Employee currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            return new Employee();
        }
        return currentUser.getEmployee();
    }

}
