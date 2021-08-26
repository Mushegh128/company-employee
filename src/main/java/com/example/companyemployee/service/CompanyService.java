package com.example.companyemployee.service;

import com.example.companyemployee.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    void save(Company company);

    void deleteById(int id);
}
