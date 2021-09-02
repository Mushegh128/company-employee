package com.example.companyemployee.repository;

import com.example.companyemployee.model.Comment;
import com.example.companyemployee.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findAllByTopic(Topic topic);
}
