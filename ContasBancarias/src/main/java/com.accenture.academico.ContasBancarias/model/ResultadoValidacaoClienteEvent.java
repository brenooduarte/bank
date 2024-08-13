package com.accenture.academico.ContasBancarias.model;

import com.accenture.academico.ContasBancarias.model.enums.StatusConta;

public record ResultadoValidacaoClienteEvent(
        Integer idContaBancaria,
        StatusConta statusConta
) {}

