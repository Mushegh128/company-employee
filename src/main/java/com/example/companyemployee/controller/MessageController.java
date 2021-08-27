package com.example.companyemployee.controller;

import com.example.companyemployee.service.impl.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageServiceImpl messageService;

    @PostMapping("/sendMessage/{fromId}to{id}")
    public String sendMessagePost(@PathVariable int fromId, @PathVariable int id) {
        messageService.save(fromId, id);
        return "redirect:/companiesEmployees/" + fromId;
    }

}
