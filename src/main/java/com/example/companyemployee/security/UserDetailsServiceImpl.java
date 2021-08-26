package com.example.companyemployee.security;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.service.impl.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MainServiceImpl mainService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Object byEmail = mainService.findByEmail(s);
        if (byEmail != null) {
            if (byEmail instanceof Employee) {
                Employee employee = (Employee) byEmail;
                return new CurrentUser(employee);
            } else if(byEmail instanceof Company) {
                Company company = (Company) byEmail;
                return new CurrentUser(company);
            }
        }
        throw new UsernameNotFoundException("does not exists");
    }
}
