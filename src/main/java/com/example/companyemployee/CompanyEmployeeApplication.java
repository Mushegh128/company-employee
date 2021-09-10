package com.example.companyemployee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CompanyEmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyEmployeeApplication.class, args);
    }

}
