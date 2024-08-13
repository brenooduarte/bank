package com.accenture.academico.OperacoesBancarias.domain.infra.query;

import com.accenture.academico.OperacoesBancarias.domain.model.Operacao;

import java.util.List;

public interface OperacaoRepositoryQuery {
    List<Operacao> listarExtratoPorConta(Integer contaId);
}
