package com.accenture.academico.model.dto.view;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaBancariaDTO {
    private BigDecimal saldo;
    private String tipo;
}