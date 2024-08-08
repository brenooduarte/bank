package com.accenture.academico.model;

import com.accenture.academico.model.enums.TipoConta;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_conta_bancaria")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta_bancaria")
    private Integer idContaBancaria;

    @Column(length = 45, nullable = false)
    private String numero;

    @Column(precision = 11, scale = 2, nullable = false)
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta", length = 15, nullable = false)
    private TipoConta tipoConta;

    @OneToOne
    @JoinColumn(name = "id_agencia", nullable = false)
    private Agencia agencia;

    @OneToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

}
