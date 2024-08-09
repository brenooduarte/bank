package com.accenture.academico.model;

import com.accenture.academico.model.enums.TipoOperacao;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_operacao")
public class Operacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacao")
    private Integer idOperacao;

    @Column(name = "data_hora_movimento", nullable = false)
    private LocalDateTime dataHoraMovimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_operacao", length = 15, nullable = false)
    private TipoOperacao tipoOperacao;

    @Column(name = "taxa_operacao", precision = 5, scale = 2, nullable = false)
    private BigDecimal taxaOperacao;

    @ManyToOne
    @JoinColumn(name = "id_conta_origem", nullable = false)
    private ContaBancaria contaOrigem;

    public Operacao(TipoOperacao tipoOperacao, BigDecimal taxaOperacao, ContaBancaria contaOrigem) {
        this.dataHoraMovimento = LocalDateTime.now();
        this.tipoOperacao = tipoOperacao;
        this.taxaOperacao = taxaOperacao;
        this.contaOrigem = contaOrigem;
    }

    public Operacao() {}
}
