package com.accenture.academico.ContasBancarias.mensageria.model;

public record ValidacaoClienteEvent(
        Integer idContaBancaria,
        Integer idCliente
) {}

