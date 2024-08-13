package com.accenture.academico.ContasBancarias.model;

public record ValidacaoClienteEvent(
        Integer idContaBancaria,
        Integer idCliente
) {}

