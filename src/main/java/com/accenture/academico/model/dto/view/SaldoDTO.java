package com.accenture.academico.model.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class SaldoDTO {
    private BigDecimal saldo;
}
