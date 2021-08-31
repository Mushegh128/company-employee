package com.example.companyemployee.repository;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Optional<Company> findByEmail(String email);
}
