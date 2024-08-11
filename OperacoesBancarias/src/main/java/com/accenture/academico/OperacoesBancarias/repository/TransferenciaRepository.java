package com.accenture.academico.OperacoesBancarias.repository;

import com.accenture.academico.OperacoesBancarias.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
}
