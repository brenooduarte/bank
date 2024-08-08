package com.accenture.academico.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transferencia")
    private Integer idTransferencia;

    @OneToOne
    @JoinColumn(name = "id_operacao")
    private Operacao operacao;

    @ManyToOne
    @JoinColumn(name = "id_conta_destino")
    private ContaBancaria contaDestino;

}
