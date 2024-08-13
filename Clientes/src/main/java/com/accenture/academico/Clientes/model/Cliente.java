package com.accenture.academico.Clientes.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id;

    @Column(length = 45, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 20, nullable = false)
    private String CPF;

    @Column(length = 20, nullable = false)
    private String telefone;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Column(name = "id_agencia")
    private Integer idAgencia;

}
