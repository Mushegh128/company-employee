package com.example.companyemployee.repository;

import com.example.companyemployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Modifying
    @Query
    List<Employee> getEmployeeByCompanyId(int id);

    @Modifying
    @Query
    Optional<Employee> findByEmail(String s);

}
