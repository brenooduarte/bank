package com.accenture.academico.Clientes.model;

public record ValidacaoClienteEvent(
        Integer idContaBancaria,
        Integer idCliente
) {}

