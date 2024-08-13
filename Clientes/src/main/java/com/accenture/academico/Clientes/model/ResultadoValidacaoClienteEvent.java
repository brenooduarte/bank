package com.accenture.academico.Clientes.model;

import com.accenture.academico.Clientes.model.enums.StatusConta;

public record ResultadoValidacaoClienteEvent(
        Integer idContaBancaria,
        StatusConta statusConta
) {}

