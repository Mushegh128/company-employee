package com.example.companyemployee.service;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.model.Role;
import com.example.companyemployee.repository.CompanyRepository;
import com.example.companyemployee.repository.EmployeeRepository;
import com.example.companyemployee.security.CurrentUser;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Employee> findByCompany(Company company){
        return employeeRepository.findByCompany(company);
    }

    public Optional<Employee> findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }

    public void registrationEmployee(Employee employee) {
        Optional<Company> byEmail = companyRepository.findByName("JFC(Job Finder Company");
        byEmail.ifPresent(employee::setCompany);
        employee.setRole(Role.EMPLOYEE);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        new CurrentUser(employee);
    }


    public Optional<Employee> findById(int id) {
       return employeeRepository.findById(id);
    }
}
