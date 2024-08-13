package com.accenture.academico.ContasBancarias.domain.model;

public record ValidacaoClienteEvent(
        Integer idContaBancaria,
        Integer idCliente
) {}

