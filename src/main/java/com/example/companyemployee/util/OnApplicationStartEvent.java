package com.example.companyemployee.util;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.model.Role;
import com.example.companyemployee.repository.CompanyRepository;
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
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        if (employeeRepository.findByEmail("admin@mail.com").isEmpty()) {
            Company company = Company.builder()
                    .name("JFC(Job Finder Company")
                    .address("Armenia")
                    .build();
            companyRepository.save(company);
            employeeRepository.save(Employee.builder()
                    .email("admin@mail.com")
                    .password(passwordEncoder.encode("admin"))
                    .surname("admin")
                    .name("admin")
                    .company(company)
                    .role(Role.SUPER_ADMIN)
                    .build());
        }

    }
}
