package com.accenture.academico.ContasBancarias.domain.model;

import com.accenture.academico.ContasBancarias.domain.enums.TipoOperacao;

import java.math.BigDecimal;

public record MensagemOperacao(TipoOperacao tipoOperacao, BigDecimal valor, Integer idContaOrigem, Integer idContaDestino) {

}
