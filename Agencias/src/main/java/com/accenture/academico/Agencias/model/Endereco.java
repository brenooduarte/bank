package com.accenture.academico.Agencias.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    @Column(length = 200, nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private int cep;

    @Column(nullable = false)
    private int numero;

    @Column(length = 300)
    private String complemento;

    @Column(length = 50, nullable = false)
    private String estado;

    @Column(length = 150, nullable = false)
    private String cidade;

}
