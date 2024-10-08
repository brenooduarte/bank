package com.accenture.academico.ContasBancarias.domain.repository;

import com.accenture.academico.ContasBancarias.domain.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {

    Optional<ContaBancaria> findById(Integer id);
    List<ContaBancaria> findByIdCliente(Integer idCliente);
}
