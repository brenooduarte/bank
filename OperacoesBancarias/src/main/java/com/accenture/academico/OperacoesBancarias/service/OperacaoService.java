package com.accenture.academico.OperacoesBancarias.service;

import com.accenture.academico.OperacoesBancarias.model.Operacao;
import com.accenture.academico.OperacoesBancarias.repository.OperacaoRepository;
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
