package com.accenture.academico.ContasBancarias.mensageria.model;

import com.accenture.academico.ContasBancarias.domain.enums.StatusConta;

public record ResultadoValidacaoClienteEvent(
        Integer idContaBancaria,
        StatusConta statusConta
) {}

