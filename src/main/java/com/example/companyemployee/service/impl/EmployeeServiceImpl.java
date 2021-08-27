package com.example.companyemployee.service.impl;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.repository.CompanyRepository;
import com.example.companyemployee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements com.example.companyemployee.service.EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<Employee> getEmployeeByCompanyId(int id) {
        return employeeRepository.getEmployeeByCompanyId(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        Optional<Company> companyOptional = companyRepository.findById(employee.getCompany().getId());
        Company company = companyOptional.get();
        int size = company.getSize();
        company.setSize(++size);
        companyRepository.save(company);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee employeePr = employee.get();
            Optional<Company> companyOpt = companyRepository.findById(employeePr.getCompany().getId());
            Company company = companyOpt.get();
            int size = company.getSize();
            company.setSize(--size);
            companyRepository.save(company);
        }
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public Employee findById(int id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }
}
