package com.example.companyemployee.controller;

import com.example.companyemployee.model.Topic;
import com.example.companyemployee.security.CurrentUser;
import com.example.companyemployee.service.CommentService;
import com.example.companyemployee.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;
    private final CommentService commentService;

    @GetMapping("/topic/byCompany")
    public String allTopicsByCompany(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Topic> topics = topicService.findAllByCompany(currentUser.getEmployee().getCompany());
        modelMap.addAttribute("topics", topics);
        return "home/topic";
    }

    @PostMapping("/topic/add")
    public String addTopicPost(@AuthenticationPrincipal CurrentUser currentUser, @ModelAttribute Topic topic) {
        topic.setCompany(currentUser.getEmployee().getCompany());
        topic.setEmployee(currentUser.getEmployee());
        topic.setDate(new Date());
        topicService.save(topic);
        return "redirect:/topic/byCompany";
    }

}
