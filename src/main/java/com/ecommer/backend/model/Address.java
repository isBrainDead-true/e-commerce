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

    private String zipcode;
    private String street;
    private String number;
    private String neighbor;
    private String estate;  

}
