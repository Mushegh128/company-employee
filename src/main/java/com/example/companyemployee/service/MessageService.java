package com.example.companyemployee.service;

import com.example.companyemployee.model.Employee;
import com.example.companyemployee.model.Message;
import com.example.companyemployee.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> findDialog(Employee fromEmployee, Employee toEmployee) {
        List<Message> dialogList = messageRepository.findAllByFromEmployeeAndToEmployee(fromEmployee, toEmployee);
        return dialogList;
    }

    public void save(Message message) {
        messageRepository.save(message);
    }
}
