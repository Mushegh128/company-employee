package com.example.companyemployee.service;

import com.example.companyemployee.model.Comment;
import com.example.companyemployee.model.Topic;
import com.example.companyemployee.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Optional<Comment> findById(int id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findAllByTopic(Topic topic) {
        return commentRepository.findAllByTopic(topic);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}
