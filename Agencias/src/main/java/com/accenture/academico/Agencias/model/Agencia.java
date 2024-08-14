package com.accenture.academico.Agencias.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_agencia")
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agencia")
    private Integer idAgencia;

    @Column(length = 45, nullable = false)
    private String nome;

    @Column(length = 15, nullable = false)
    private String telefone;

    @OneToOne
    @JoinColumn(name = "id_endereco", nullable = false)
    private Endereco endereco;
}
