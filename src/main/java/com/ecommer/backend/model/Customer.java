package com.ecommer.backend.model;

import com.ecommer.backend.profile.Authority;
import com.ecommer.backend.profile.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Authority role;


}
