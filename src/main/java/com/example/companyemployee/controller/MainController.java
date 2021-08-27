package com.example.companyemployee.controller;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.security.CurrentUser;
import com.example.companyemployee.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final CompanyService companyService;

    @GetMapping("/")
    public String main(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null && currentUser.isEnabled()) {
            if (currentUser.getEmployee() != null) {
                Employee employee = currentUser.getEmployee();
                //int id = employee.getCompany().getId();
                return "redirect:/companiesEmployees/" + 15;
            } else if (currentUser.getCompany() != null) {
                int id = currentUser.getCompany().getId();
                return "redirect:/companiesEmployees/" + id;
            }
        }
        return "index";
    }

    @GetMapping("/registrationCompany")
    public String regCompany() {
        return "registrationCompany";
    }

    @GetMapping("/registrationEmployee")
    public String regEmployee(ModelMap modelMap) {
        List<Company> companies = companyService.findAll();
        modelMap.addAttribute("companies", companies);
        return "registrationEmployee";
    }


}
