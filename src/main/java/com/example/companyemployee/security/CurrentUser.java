package com.example.companyemployee.security;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import org.springframework.security.core.authority.AuthorityUtils;


public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private Company company;
    private Employee employee;

    public CurrentUser(Company company) {

        super(company.getEmail(), company.getPassword(), AuthorityUtils.createAuthorityList("COMPANY"));
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public CurrentUser(Employee employee) {

        super(employee.getEmail(), employee.getPassword(), AuthorityUtils.createAuthorityList("EMPLOYEE"));
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }


}
