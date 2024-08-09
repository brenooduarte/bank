package com.accenture.academico.infra.impl;

import com.accenture.academico.infra.query.OperacaoRepositoryQuery;
import com.accenture.academico.model.Operacao;
import com.accenture.academico.repository.OperacaoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperacaoRepositoryImpl implements OperacaoRepositoryQuery {

    @PersistenceContext
    EntityManager entityManager;

    public List<Operacao> listarExtratoPorConta(Integer contaId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Operacao> cq = cb.createQuery(Operacao.class);
        Root<Operacao> root = cq.from(Operacao.class);

        cq.where(
                cb.equal(root.get("contaOrigem").get("idContaBancaria"), contaId)
        );

        return entityManager.createQuery(cq).getResultList();
    }
}
