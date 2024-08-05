package com.accenture.academico.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_conta_corrente")
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "limite_cheque_especial", precision = 15, scale = 2, nullable = false)
    private BigDecimal limiteChequeEspecial;

    @OneToOne
    @JoinColumn(name = "id_conta_bancaria")
    private ContaBancaria contaBancaria;

}
