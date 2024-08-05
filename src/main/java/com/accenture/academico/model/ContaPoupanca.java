package com.accenture.academico.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_conta_poupanca")
public class ContaPoupanca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "taxa_juros", precision = 5, scale = 2, nullable = false)
    private BigDecimal taxaJuros;

    @OneToOne
    @JoinColumn(name = "id_conta_bancaria")
    private ContaBancaria contaBancaria;

}
