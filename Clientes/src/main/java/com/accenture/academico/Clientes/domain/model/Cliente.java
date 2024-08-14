package com.accenture.academico.Clientes.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

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

    @Column(length = 20, nullable = false)
    private String senha;

    @Column(name = "cpf", length = 20, nullable = false)
    private String CPF;

    @Column(length = 20, nullable = false)
    private String telefone;

    @Column(name = "id_agencia")
    private Integer idAgencia;


//    private Integer codigoConta;

//    private Integer codigoAgencia;

}
