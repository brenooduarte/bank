package com.accenture.academico.OperacoesBancarias.infra.query;

import com.accenture.academico.OperacoesBancarias.model.Operacao;

import java.util.List;

public interface OperacaoRepositoryQuery {
    List<Operacao> listarExtratoPorConta(Integer contaId);
}
