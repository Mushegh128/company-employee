package com.example.companyemployee.service;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.model.Role;
import com.example.companyemployee.repository.CompanyRepository;
import com.example.companyemployee.repository.EmployeeRepository;
import com.example.companyemployee.security.CurrentUser;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    @Value("mainEmail")
    String mainEmail;
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public List<Employee> findByCompany(Company company){
        return employeeRepository.findByCompany(company);
    }

    public Optional<Employee> findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }

    public void save(Employee employee) {
        Optional<Company> byEmail = companyRepository.findByEmail(mainEmail);
        byEmail.ifPresent(employee::setCompany);
        employee.setRole(Role.EMPLOYEE);
        employeeRepository.save(employee);
        new CurrentUser(employee);
    }


    public Optional<Employee> findById(int id) {
       return employeeRepository.findById(id);
    }
}
