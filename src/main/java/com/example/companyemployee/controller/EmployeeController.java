package com.example.companyemployee.controller;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.security.CurrentUser;
import com.example.companyemployee.service.EmployeeService;
import com.example.companyemployee.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;
    private final MailService mailService;

//    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/employee/add")
    public String addEmployeePost(@ModelAttribute Employee employee) {
        employeeService.registrationEmployee(employee);
        mailService.sendSimpleMessage("leninakanvdvdv@bk.ru","add User controllor","kuku");
        return "redirect:/login";
    }

    @GetMapping("/employeeHome")
    public String employeeHome(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        Company company = currentUser.getEmployee().getCompany();
        List<Employee> byCompany = employeeService.findByCompany(company);
        modelMap.addAttribute("byCompany", byCompany);
        log.info("employee open home page, company employee size = {}", byCompany.size());
        return "home/employee";
    }


}
