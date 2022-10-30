package com.ecommer.backend.util;

import com.ecommer.backend.model.Customer;
import com.ecommer.backend.profile.Authority;
import com.ecommer.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
public class DataInjection implements CommandLineRunner {

    @Autowired
    private CustomerService service;

    @Override
    public void run(String... args) throws Exception {

        final Customer admin = new Customer();
        admin.setPassword(new BCryptPasswordEncoder().encode("123456"));
        admin.setUsername("cliente");

        service.create(admin);

    }
}
