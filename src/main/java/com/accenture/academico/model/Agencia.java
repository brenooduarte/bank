package com.accenture.academico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

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

    @Column(name = "taxa_juros", precision = 5, scale = 2, nullable = false)
    private BigDecimal taxaJuros;

    @ManyToMany(mappedBy = "agencias")
    private List<Cliente> clientes;

    @OneToOne
    @JoinColumn(name = "id_endereco", nullable = false)
    private Endereco endereco;

}
