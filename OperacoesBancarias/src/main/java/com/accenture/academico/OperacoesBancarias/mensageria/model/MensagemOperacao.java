package com.accenture.academico.OperacoesBancarias.mensageria.model;

import com.accenture.academico.OperacoesBancarias.domain.enums.TipoOperacao;

import java.math.BigDecimal;

public record MensagemOperacao(TipoOperacao tipoOperacao, BigDecimal valor, Integer idContaOrigem, Integer idContaDestino) {

}
