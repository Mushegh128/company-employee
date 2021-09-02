package com.example.companyemployee.controller;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.security.CurrentUser;
import com.example.companyemployee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping("/employee/add")
    public String addEmployeePost(@ModelAttribute Employee employee) {
        employeeService.registrationEmployee(employee);
        return "redirect:/login";
    }

    @GetMapping("/employeeHome")
    public String employeeHome(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        Company company = currentUser.getEmployee().getCompany();
        List<Employee> byCompany = employeeService.findByCompany(company);
        modelMap.addAttribute("byCompany", byCompany);
        return "home/employee";
    }


}
