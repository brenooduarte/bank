package com.accenture.academico.repository;

import com.accenture.academico.model.Agencia;
import com.accenture.academico.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {
//    List<Cliente> findByAgenciasId(Integer id);

}
