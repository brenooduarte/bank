package com.accenture.academico.Clientes.mensageria.model;

import com.accenture.academico.Clientes.domain.enums.StatusConta;

public record ResultadoValidacaoClienteEvent(
        Integer idContaBancaria,
        StatusConta statusConta
) {}

