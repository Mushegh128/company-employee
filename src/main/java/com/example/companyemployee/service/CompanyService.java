package com.example.companyemployee.service;

import com.example.companyemployee.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> findAll();

    void save(Company company);

    void deleteById(int id);

    Optional<Company> findCompanyByEmail(String s);
}
