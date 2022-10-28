package com.ecommer.backend.service;

import com.ecommer.backend.profile.CurrentUserApp;
import com.ecommer.backend.repository.SysUserRepository;
import com.ecommer.backend.repository.UserInMemoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrentUserService implements UserDetailsService {

    private final SysUserRepository repository;
    private final UserInMemoryRepository userInMemoryRepository;

    @Override
    public CurrentUserApp loadUserByUsername(String username) throws UsernameNotFoundException {
        CurrentUserApp currentUserApp = repository.findByUsername(username);
        if (currentUserApp == null) System.out.println("a");
        return currentUserApp;
    }


}
