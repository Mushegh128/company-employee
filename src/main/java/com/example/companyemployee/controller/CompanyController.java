package com.example.companyemployee.controller;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.service.CompanyService;
import com.example.companyemployee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @GetMapping("/companies")
    public String companies(ModelMap modelMap) {
        List<Company> companies = companyService.findAll();
        modelMap.addAttribute("companies", companies);
        return "/companies";
    }

    @GetMapping("/addCompany")
    public String addCompany() {
        return "addCompany";
    }

    @PostMapping("/addCompany")
    public String addCompanyPost(@ModelAttribute Company company) {
        companyService.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable int id) {

        companyService.deleteById(id);

        return "redirect:/companies";
    }

    @GetMapping("/companiesEmployees/{id}")
    public String companiesEmployees(@PathVariable int id, ModelMap modelMap) {
        List<Employee> employeeByCompanyId = employeeService.getEmployeeByCompanyId(id);
        modelMap.addAttribute("employees", employeeByCompanyId);
        return "employees";
    }

}
