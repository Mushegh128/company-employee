package com.example.companyemployee.repository;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Integer> {

    List<Topic> findAllByCompany(Company company);
}
