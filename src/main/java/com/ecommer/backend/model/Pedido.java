package com.ecommer.backend.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static int sequencia = 241998;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer cliente;
    private int numeroPed = 0;

    @OneToOne
    @NotNull
    private Item itensPedido;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date data;

    private int formaPagamento;

    private double totalPedido;

    public Pedido(){
        this.numeroPed = sequencia;
        sequencia++;
    }
}
