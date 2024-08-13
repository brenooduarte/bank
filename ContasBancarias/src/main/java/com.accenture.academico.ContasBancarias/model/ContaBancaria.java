package com.accenture.academico.ContasBancarias.model;

import com.accenture.academico.ContasBancarias.model.enums.StatusConta;
import com.accenture.academico.ContasBancarias.model.enums.TipoConta;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DecimalFormat;

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

    @Column(name = "id_agencia", nullable = false)
    private Integer idAgencia;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private StatusConta statusConta = StatusConta.PENDENTE;

    private static final SecureRandom random = new SecureRandom();
    private static final DecimalFormat numberFormat = new DecimalFormat("00000");

    public ContaBancaria(BigDecimal saldo, TipoConta tipoConta, Integer idAgencia, Integer idCliente) {
        this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.idAgencia = idAgencia;
        this.idCliente = idCliente;
        this.numero = generateAccountNumber();
        this.statusConta = StatusConta.PENDENTE;
    }

    public ContaBancaria() {}

    private String generateAccountNumber() {
        int randomNumber = random.nextInt(100_000);
        return numberFormat.format(randomNumber);
    }
}
