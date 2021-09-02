package com.example.companyemployee.repository;

import com.example.companyemployee.model.Employee;
import com.example.companyemployee.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    List<Message> findAllByFromEmployeeAndToEmployee(Employee fromEmployee, Employee toEmployee);
}
