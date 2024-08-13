package com.accenture.academico.OperacoesBancarias.domain.repository;

import com.accenture.academico.OperacoesBancarias.domain.infra.query.OperacaoRepositoryQuery;
import com.accenture.academico.OperacoesBancarias.domain.model.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Integer>, OperacaoRepositoryQuery {
}
