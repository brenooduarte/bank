package com.accenture.academico.repository;

import com.accenture.academico.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {

    List<ContaBancaria> findByClienteId(Integer clienteId);
    Optional<ContaBancaria> findById(Integer id);
    List<ContaBancaria> findByCliente_Id(Integer clienteId);

}
