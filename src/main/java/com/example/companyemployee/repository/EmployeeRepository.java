package com.example.companyemployee.repository;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

     Optional<Employee> findByEmail(String email);

     List<Employee> findByCompany(Company company);

}
