package com.example.companyemployee.controller;

import com.example.companyemployee.model.Employee;
import com.example.companyemployee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;



    @PostMapping("/employee/add")
    public String addEmployeePost(@ModelAttribute Employee employee, ModelMap modelMap){
        employeeService.save(employee);
        List<Employee> byCompany = employeeService.findByCompany(employee.getCompany());
        modelMap.addAttribute("byCompany", byCompany);
        return "home/employee";
    }

//    @PostMapping("/home/employee")
//    public String homeEmployee(){
//
//    }

}
