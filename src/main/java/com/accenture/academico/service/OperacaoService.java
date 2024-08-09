package com.accenture.academico.service;

import com.accenture.academico.model.Operacao;
import com.accenture.academico.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacaoService {

    @Autowired
    OperacaoRepository operacaoRepository;

    public List<Operacao> listarExtratoPorConta(Integer contaId) {
        return operacaoRepository.listarExtratoPorConta(contaId);
    }

}
