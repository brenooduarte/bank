package com.accenture.academico.ContasBancarias.model;

import com.accenture.academico.ContasBancarias.model.enums.TipoOperacao;

import java.math.BigDecimal;

public record MensagemOperacao(TipoOperacao tipoOperacao, BigDecimal valor, Integer idContaOrigem, Integer idContaDestino) {

}
