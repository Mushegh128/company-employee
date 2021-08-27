package com.example.companyemployee.controller;

import com.example.companyemployee.model.Message;
import com.example.companyemployee.security.CurrentUser;
import com.example.companyemployee.service.impl.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageServiceImpl messageService;

    @PostMapping("/employee{fromId}/answer{toId}")
    public String sendMessagePost(@PathVariable int fromId, @PathVariable int toId) {
        messageService.save(fromId, toId);
        return "redirect:/companiesEmployees/" + fromId;
    }

    @PostMapping
    public String allMessagesByCurrentUser(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        int id = currentUser.getEmployee().getId();
        List<Message> allByToId = messageService.findAllByToId(id);
        modelMap.addAttribute("allByToId", allByToId);
        return "redirect:/allMassegesByEmployee";
    }


}
