package com.ecommer.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String cep;
    private String rua;
    private String numero;
    private String bairro;

}
