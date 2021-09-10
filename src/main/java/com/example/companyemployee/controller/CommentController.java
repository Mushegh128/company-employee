package com.example.companyemployee.controller;

import com.example.companyemployee.model.Comment;
import com.example.companyemployee.model.Topic;
import com.example.companyemployee.security.CurrentUser;
import com.example.companyemployee.service.CommentService;
import com.example.companyemployee.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final TopicService topicService;
    private final CommentService commentService;


    @PostMapping("/comment/add")
    public String addComment(@RequestParam("description") String description, @RequestParam("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<Topic> topicOpt = topicService.findById(id);
        Topic topic = topicOpt.get();
        commentService.save(Comment.builder()
                .description(description)
                .topic(topic)
                .employee(currentUser.getEmployee())
                .build());

        return "redirect:/comment/byTopic/" + topic.getId();
    }

    @GetMapping("/comment/byTopic/{id}")
    public String commentsByTopic(@PathVariable int id, ModelMap modelMap) {
        Optional<Topic> topicOpt = topicService.findById(id);
        if (topicOpt.isPresent()) {
            List<Comment> comments = commentService.findAllByTopic(topicOpt.get());
            modelMap.addAttribute("comments", comments);
            modelMap.addAttribute("topic", topicOpt.get());
            return "home/topicSingle";
        }
        return "redirect:/home/topic";
    }

}
