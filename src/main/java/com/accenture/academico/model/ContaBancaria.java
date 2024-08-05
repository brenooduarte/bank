package com.accenture.academico.model;

import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_conta_bancaria")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String numero;

    @Column(precision = 11, scale = 2, nullable = false)
    private BigDecimal saldo;

    @OneToOne
    @JoinColumn(name = "id_agencia", nullable = false)
    private Agencia agencia;

    @OneToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
}
