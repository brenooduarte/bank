package com.accenture.academico.repository;

import com.accenture.academico.infra.query.OperacaoRepositoryQuery;
import com.accenture.academico.model.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Integer>, OperacaoRepositoryQuery {
}
