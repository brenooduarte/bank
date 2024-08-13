package com.accenture.academico.OperacoesBancarias.service;

import com.accenture.academico.OperacoesBancarias.model.MensagemOperacao;
import com.accenture.academico.OperacoesBancarias.model.Operacao;
import com.accenture.academico.OperacoesBancarias.model.enums.TipoOperacao;
import com.accenture.academico.OperacoesBancarias.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OperacaoService {

    @Autowired
    OperacaoRepository operacaoRepository;

    public List<Operacao> listarExtratoPorConta(Integer contaId) {
        return operacaoRepository.listarExtratoPorConta(contaId);
    }

    public void realizarDeposito(MensagemOperacao mensagem) {
        Operacao operacao = new Operacao(TipoOperacao.DEPOSITO, BigDecimal.ZERO, mensagem.idContaOrigem());
        operacaoRepository.save(operacao);
    }

    public void realizarSaque(MensagemOperacao mensagem) {
        Operacao operacao = new Operacao(TipoOperacao.SAQUE, BigDecimal.ZERO, mensagem.idContaOrigem());
        operacaoRepository.save(operacao);
    }

    public void realizarTransferencia(MensagemOperacao mensagem) {
        Operacao operacao = new Operacao(TipoOperacao.TRANSFERENCIA, BigDecimal.ZERO, mensagem.idContaOrigem(), mensagem.idContaDestino());
        operacaoRepository.save(operacao);
    }

}
