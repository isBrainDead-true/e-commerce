package com.ecommer.backend.profile;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToOne(targetEntity = CurrentUserApp.class)
    private Set<CurrentUserApp> users;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
