package com.ecommer.backend.controller;

import com.ecommer.backend.model.Customer;
import com.ecommer.backend.service.CustomerService;
import com.ecommer.backend.service.CurrentUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService service;
    private final CurrentUserService sysService;

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

    @GetMapping("/get/{username}")
    public ResponseEntity<Customer> readByUsername(@RequestParam String username){
        Customer cstm = service.loadUserByUsername(username);
        return new ResponseEntity<Customer>(cstm, HttpStatus.OK);
    }

    @GetMapping("/auth/{username}/{password}")
    public boolean auth(@RequestParam String username, String password){

        System.out.println(username);
        System.out.printf(password);

        return true;
    }


    @PatchMapping("/update")
    public ResponseEntity<Customer> update(@RequestBody Customer customer){
        Customer cstm = service.create(customer);
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
