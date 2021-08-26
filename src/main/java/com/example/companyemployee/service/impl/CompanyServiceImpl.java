package com.example.companyemployee.service.impl;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements com.example.companyemployee.service.CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteById(int id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> findCompanyByEmail(String s) {
        return companyRepository.findCompanyByEmail(s);
    }
}
