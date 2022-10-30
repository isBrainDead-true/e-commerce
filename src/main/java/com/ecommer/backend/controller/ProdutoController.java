package com.ecommer.backend.controller;

import com.ecommer.backend.model.Produto;
import com.ecommer.backend.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("new")
    public ResponseEntity<Produto> create(@RequestBody Produto prod){
        Produto pr = service.create(prod);
        return new ResponseEntity<Produto>(pr, HttpStatus.OK);
    }
}
