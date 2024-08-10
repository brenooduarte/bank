package com.accenture.academico.model.dto.view;

import java.math.BigDecimal;

public record ContaBancariaDTO(
        BigDecimal saldo,
        String tipo
) {
}