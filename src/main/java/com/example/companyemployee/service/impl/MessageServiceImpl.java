package com.example.companyemployee.service.impl;

import com.example.companyemployee.model.Message;
import com.example.companyemployee.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl {

    private final MessageRepository messageRepository;
    private final EmployeeServiceImpl employeeService;

    public void save(int fromId, int id) {
        messageRepository.save(Message.builder()
                .fromId(employeeService.findById(fromId))
                .toId(employeeService.findById(id))
                .build());
    }

    public List<Message> findAllByToId(int id) {
        return messageRepository.findByToId(id);
    }
}
