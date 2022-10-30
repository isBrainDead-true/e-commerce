package com.ecommer.backend.service;

import com.ecommer.backend.model.Produto;
import com.ecommer.backend.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoService {

    private ProdutoRepository repository;

    public Produto getProdutoById(Long id){
        Optional<Produto> prod = repository.findById(id);
        if (prod == null) System.out.println("Produto não encontrado");
        return prod.get();
    }

    public Produto create(Produto prod){
        return repository.save(prod);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Produto> getAllProds(){
        return repository.findAll();
    }

}
