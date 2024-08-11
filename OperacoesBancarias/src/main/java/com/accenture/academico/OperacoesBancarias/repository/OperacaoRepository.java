package com.accenture.academico.OperacoesBancarias.repository;

import com.accenture.academico.OperacoesBancarias.infra.query.OperacaoRepositoryQuery;
import com.accenture.academico.OperacoesBancarias.model.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Integer>, OperacaoRepositoryQuery {
}
