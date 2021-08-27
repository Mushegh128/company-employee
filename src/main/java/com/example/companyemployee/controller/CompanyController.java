package com.example.companyemployee.controller;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.security.CurrentUser;
import com.example.companyemployee.service.CompanyService;
import com.example.companyemployee.service.EmployeeService;
import com.example.companyemployee.service.impl.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final MessageServiceImpl messageService;


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

        Optional<Company> companyByEmail = companyService.findCompanyByEmail(company.getEmail());
        Optional<Employee> employeeByEmail = employeeService.findByEmail(company.getEmail());
        if (companyByEmail.isPresent() || employeeByEmail.isPresent()) {
            return "redirect:/";
        }
        companyService.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable int id) {
        companyService.deleteById(id);
        return "redirect:/companies";
    }

    @GetMapping("/companiesEmployees/{id}")
    public String companiesEmployees(@PathVariable int id, ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Employee> employeeByCompanyId = employeeService.getEmployeeByCompanyId(id);
        modelMap.addAttribute("employees", employeeByCompanyId);
        if (currentUser.getEmployee() != null) {
            messageService.findAllByToId(id);
            modelMap.addAttribute("currentUser", currentUser);
        }
        return "employees";
    }

}
