package com.example.companyemployee.security;

import com.example.companyemployee.model.User;
import com.example.companyemployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userByEmail = userRepository.findUserByEmail(s);
        if (userByEmail.isPresent()) {
            return new CurrentUser(userByEmail.get());
        } else {
            return null;
        }
    }
}
