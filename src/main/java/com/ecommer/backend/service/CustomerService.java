package com.ecommer.backend.service;

import com.ecommer.backend.model.Customer;
import com.ecommer.backend.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Customer create(Customer customer){
        return this.repository.save(customer);
    }

    public Optional<Customer> read(Long id){
        return this.repository.findById(id);
    }

    public Customer update(Customer customer){
        return this.repository.save(customer);
    }

    public void delete(Long id){
        this.repository.deleteById(id);
    }

    public List<Customer> findAll(){
        List<Customer> customers = repository.findAll();
        return customers;
    }

    public Customer loadUserByUsername(String username) {
        return this.repository.findByUsername(username);
    }
}
