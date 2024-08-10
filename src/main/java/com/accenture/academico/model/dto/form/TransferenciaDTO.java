package com.accenture.academico.model.dto.form;

import java.math.BigDecimal;

public record TransferenciaDTO(
        Integer contaOrigemId,
        Integer contaDestinoId,
        BigDecimal valor
) {
}
