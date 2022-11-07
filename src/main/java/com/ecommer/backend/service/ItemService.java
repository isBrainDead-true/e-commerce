package com.ecommer.backend.service;

import com.ecommer.backend.model.Item;
import com.ecommer.backend.repository.ItensRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItensRepository repository;

    public Item salvar(Item item){
       return repository.save(item);
    }


}
