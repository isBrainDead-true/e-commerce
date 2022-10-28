package com.ecommer.backend.model;

import com.ecommer.backend.profile.Authority;
import com.ecommer.backend.profile.CurrentUserApp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends CurrentUserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String employeeNumber;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Authority jobFunction;

}

