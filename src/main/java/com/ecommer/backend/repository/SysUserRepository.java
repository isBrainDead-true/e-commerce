package com.ecommer.backend.repository;

import com.ecommer.backend.profile.CurrentUserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SysUserRepository extends JpaRepository<CurrentUserApp, Long> {

    CurrentUserApp findByUsername(String username);

}
