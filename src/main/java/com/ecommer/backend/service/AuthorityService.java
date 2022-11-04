package com.ecommer.backend.service;

import com.ecommer.backend.profile.Authority;
import com.ecommer.backend.repository.AuthorityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorityService {

    private final AuthorityRepository repository;

    public Authority save(Authority role){
        return repository.save(role);
    }

}
