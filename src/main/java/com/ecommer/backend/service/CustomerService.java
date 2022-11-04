package com.ecommer.backend.service;

import com.ecommer.backend.model.Customer;
import com.ecommer.backend.profile.Authority;
import com.ecommer.backend.repository.AuthorityRepository;
import com.ecommer.backend.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final AuthorityRepository authorityRepository;


    public Customer create(Customer customer){
        String EncryptedPassword = new BCryptPasswordEncoder().encode(customer.getPassword());
        customer.setPassword(EncryptedPassword);
        Authority role_cliente = new Authority();
        role_cliente.setRole("Cliente");
        authorityRepository.save(role_cliente);
        customer.setRole(role_cliente);
        return this.repository.save(customer);
    }

    public Optional<Customer> read(Long id){
        return this.repository.findById(id);
    }

    public Customer update(Long id, Customer atualizado){
        Optional<Customer> tmp = read(id);
        tmp.get().setAddress(atualizado.getAddress());
        tmp.get().setEmail(atualizado.getEmail());
        tmp.get().setCpf(atualizado.getCpf());
        tmp.get().setPhone(atualizado.getPhone());
        return this.repository.save(tmp.get());
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
