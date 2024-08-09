package com.accenture.academico.infra.query;

import com.accenture.academico.model.Operacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OperacaoRepositoryQuery {
    List<Operacao> listarExtratoPorConta(Integer contaId);
}
