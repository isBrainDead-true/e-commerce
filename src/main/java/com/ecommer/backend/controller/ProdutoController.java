package com.ecommer.backend.controller;

import com.ecommer.backend.model.Produto;
import com.ecommer.backend.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/produto")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController {

    private ProdutoService service;

    @GetMapping("all")
    public ResponseEntity<List<Produto>> getProdutos(){
        List<Produto> produtos = service.getAllProds();
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }


}
