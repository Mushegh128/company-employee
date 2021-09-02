package com.example.companyemployee.service;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Topic;
import com.example.companyemployee.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public List<Topic> findAllByCompany(Company company) {
        return topicRepository.findAllByCompany(company);
    }

    public void save(Topic topic) {
        topicRepository.save(topic);
    }


    public Optional<Topic> findById(int id) {
        return topicRepository.findById(id);
    }
}
