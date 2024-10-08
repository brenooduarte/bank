package com.accenture.academico.Agencias.repository;

import com.accenture.academico.Agencias.model.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {

}
