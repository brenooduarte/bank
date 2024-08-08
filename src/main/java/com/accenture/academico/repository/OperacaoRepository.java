package com.accenture.academico.repository;

import com.accenture.academico.model.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Integer> {

//    List<Operacao> findByContaOrigem(Integer id);

}
