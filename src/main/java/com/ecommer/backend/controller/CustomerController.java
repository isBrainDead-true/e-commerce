package com.ecommer.backend.controller;

import com.ecommer.backend.model.Customer;
import com.ecommer.backend.service.CustomerService;
import com.ecommer.backend.service.CurrentUserService;
import com.ecommer.backend.session.InMemorySessionRegistry;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService service;
    private final CurrentUserService sysService;
    private final InMemorySessionRegistry registry;

    @PostMapping("/register")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        Customer cstm = service.create(customer);
        return new ResponseEntity<Customer>(cstm, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Customer> read(@RequestParam Long id){
        Optional<Customer> cstm = service.read(id);
        return new ResponseEntity<Customer>(cstm.get(), HttpStatus.OK);
    }

    @GetMapping("/get/user/{sessionId}")
    public ResponseEntity<Customer> readByUsername(@PathVariable("sessionId") String sessionId){
        String username = registry.getUsernameForSession(sessionId);
        Customer cstm = service.loadUserByUsername(username);
        return new ResponseEntity<Customer>(cstm, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> update(@RequestBody Customer customer){
        Customer cstm = service.create(customer);
        return new ResponseEntity<Customer>(cstm, HttpStatus.OK);
    }

    @PutMapping("/update/profile/{id}")
    public ResponseEntity<Customer> info(@PathVariable String id, @RequestBody Customer customer){
        Long idCliente = Long.parseLong(id);
        System.out.println(customer);
        Customer cstm = service.update(idCliente, customer);
        return new ResponseEntity<Customer>(cstm, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@RequestParam Long id){
        this.service.delete(id);
    }

    @GetMapping("all")
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> customers = service.findAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }



}
