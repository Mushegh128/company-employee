package com.example.companyemployee.util;

import com.example.companyemployee.model.Employee;
import com.example.companyemployee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OnApplicationStartEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        if (employeeRepository.findByEmail("admin@mail.com").isEmpty()) {
            employeeRepository.save(Employee.builder()
                    .email("admin@mail.com")
                    .password(passwordEncoder.encode("admin"))
                    .surname("admin")
                    .name("admin")
                    .build());
        }

    }
}
