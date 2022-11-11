package com.ecommer.backend.model;

import com.ecommer.backend.profile.Authority;
import com.ecommer.backend.profile.CurrentUserApp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends CurrentUserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private String email;
    private String phone;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Authority role;

    @OneToMany
    @JsonIgnore
    private List<Pedido> pedidos;



}