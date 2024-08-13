package com.accenture.academico.ContasBancarias.domain.model.dto.form;

import com.accenture.academico.ContasBancarias.domain.enums.TipoConta;
import jakarta.validation.constraints.NotNull;

public record ContaBancariaDTOForm(
        @NotNull(message = "O tipo da conta é obrigatório.")
        TipoConta tipoConta,
        @NotNull(message = "O ID da agência é obrigatório.")
        Integer idAgencia,
        @NotNull(message = "O ID do cliente é obrigatório.")
        Integer idCliente
)
{}
