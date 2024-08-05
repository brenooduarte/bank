package com.accenture.academico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tb_agencia")
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String nome;

    @Column(length = 15, nullable = false)
    private String telefone;

    @ManyToMany(mappedBy = "agencias")
    private List<Cliente> clientes;

    @OneToOne
    @JoinColumn(name = "id_endereco", nullable = false)
    private Endereco endereco;

}
