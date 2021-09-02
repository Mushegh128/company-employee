package com.example.companyemployee.controller;

import com.example.companyemployee.model.Employee;
import com.example.companyemployee.model.Message;
import com.example.companyemployee.security.CurrentUser;
import com.example.companyemployee.service.EmployeeService;
import com.example.companyemployee.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class MessageController {

    private final EmployeeService employeeService;
    private final MessageService messageService;

    @GetMapping("/sendMessage/{id}")
    public String sendMessage(@PathVariable int id, ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<Employee> byId = employeeService.findById(id);
        byId.ifPresent(employee -> modelMap.addAttribute("toEmployee", employee));
        List<Message> sendMessages = messageService.findDialog(currentUser.getEmployee(), byId.get());
        List<Message> receivedMessages = messageService.findDialog(byId.get(), currentUser.getEmployee());
        modelMap.addAttribute("fromEmployee", currentUser.getEmployee());
        modelMap.addAttribute("sendMessages", sendMessages);
        modelMap.addAttribute("receivedMessages", receivedMessages);
        return "messenger";
    }

    @PostMapping("/message/send")
    public String sendMessagePost(@ModelAttribute Message message, @RequestParam("toEmployee_id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<Employee> byId = employeeService.findById(id);
        if (byId.isPresent()) {
            Employee toEmployee = byId.get();
            message.setToEmployee(toEmployee);
            message.setFromEmployee(currentUser.getEmployee());
            messageService.save(message);
            return "redirect:/sendMessage/" + toEmployee.getId();
        }
        return "redirect:/employeeHome";
    }
}
