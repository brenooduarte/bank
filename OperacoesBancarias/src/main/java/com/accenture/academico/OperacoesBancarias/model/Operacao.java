package com.accenture.academico.OperacoesBancarias.model;

import com.accenture.academico.OperacoesBancarias.model.enums.TipoOperacao;
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

    @Column(name = "id_conta_origem", nullable = false)
    private Integer idContaOrigem;

    @Column(name = "id_conta_destino")
    private Integer idContaDestino;

    public Operacao(TipoOperacao tipoOperacao, BigDecimal taxaOperacao, Integer idContaOrigem, Integer idContaDestino) {
        this.dataHoraMovimento = LocalDateTime.now();
        this.tipoOperacao = tipoOperacao;
        this.taxaOperacao = taxaOperacao;
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
    }

    public Operacao(TipoOperacao tipoOperacao, BigDecimal taxaOperacao, Integer idContaOrigem) {
        this.dataHoraMovimento = LocalDateTime.now();
        this.tipoOperacao = tipoOperacao;
        this.taxaOperacao = taxaOperacao;
        this.idContaOrigem = idContaOrigem;
    }

    public Operacao() {}
}
