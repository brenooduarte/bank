package com.accenture.academico.model.dto.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferenciaDTO {

    private Integer contaOrigemId;
    private Integer contaDestinoId;
    private BigDecimal valor;

}
