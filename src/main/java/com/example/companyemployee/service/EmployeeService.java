package com.example.companyemployee.service;

import com.example.companyemployee.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeByCompanyId(int id);

    List<Employee> findAll();

    void save(Employee employee);

    void deleteById(int id);
}
