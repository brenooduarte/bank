package com.accenture.academico.OperacoesBancarias.model;

import com.accenture.academico.OperacoesBancarias.model.enums.TipoOperacao;

import java.math.BigDecimal;

public record MensagemOperacao(TipoOperacao tipoOperacao, BigDecimal valor, Integer idContaOrigem, Integer idContaDestino) {

}
