package com.example.companyemployee.controller;

import com.example.companyemployee.model.Employee;
import com.example.companyemployee.security.CurrentUser;
import com.example.companyemployee.service.EmployeeService;
import com.example.companyemployee.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class MessageController {

    private final EmployeeService employeeService;
    private final MessageService messageService;

    @GetMapping("/sendMessage/{id}")
    public String sendMessage(@PathVariable int id, ModelMap modelMap,@AuthenticationPrincipal CurrentUser currentUser){
        Optional<Employee> byId = employeeService.findById(id);
        byId.ifPresent(employee -> modelMap.addAttribute("toEmployee", employee));
        modelMap.addAttribute("fromEmployee",currentUser);
        modelMap.addAttribute("dialog",messageService.findDialog(currentUser,byId.get()));
        return "messenger";
    }

}
