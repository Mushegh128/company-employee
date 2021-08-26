package com.example.companyemployee.service.impl;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.repository.CompanyRepository;
import com.example.companyemployee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainServiceImpl {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public Object findByEmail(String s){
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(s);
        Optional<Company> companyOpt = companyRepository.findCompanyByEmail(s);

        if (employeeOpt.isPresent()){
            return employeeOpt.get();
        }else return companyOpt.orElse(null);
    }

}
