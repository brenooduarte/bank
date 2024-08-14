package com.accenture.academico.Clientes.domain.repository;

import com.accenture.academico.Clientes.domain.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Cliente> findByCpfAndSenha(String cpf, String senha) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> clienteRoot = criteriaQuery.from(Cliente.class);

        Predicate cpfPredicate = criteriaBuilder.equal(clienteRoot.get("CPF"), cpf);
        Predicate senhaPredicate = criteriaBuilder.equal(clienteRoot.get("senha"), senha);

        criteriaQuery.select(clienteRoot).where(criteriaBuilder.and(cpfPredicate, senhaPredicate));

        Cliente cliente = entityManager.createQuery(criteriaQuery).setMaxResults(1).getResultStream().findFirst().orElse(null);

        return Optional.ofNullable(cliente);
    }
}
