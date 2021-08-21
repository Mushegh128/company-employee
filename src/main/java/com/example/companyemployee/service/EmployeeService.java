package com.example.companyemployee.service;

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
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public List<Employee> getEmployeeByCompanyId(int id) {
        return employeeRepository.getEmployeeByCompanyId(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        Optional<Company> companyOptional = companyRepository.findById(employee.getCompany().getId());
        Company company = companyOptional.get();
        int size = company.getSize();
        company.setSize(++size);
        companyRepository.save(company);
        employeeRepository.save(employee);
    }

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
}
