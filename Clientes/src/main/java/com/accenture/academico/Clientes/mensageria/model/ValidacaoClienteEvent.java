package com.accenture.academico.Clientes.mensageria.model;

public record ValidacaoClienteEvent(
        Integer idContaBancaria,
        Integer idCliente
) {}

