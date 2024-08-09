package com.accenture.academico.service;

import com.accenture.academico.model.ContaBancaria;
import com.accenture.academico.model.Operacao;
import com.accenture.academico.model.Transferencia;
import com.accenture.academico.model.dto.form.TransferenciaDTO;
import com.accenture.academico.model.enums.TipoOperacao;
import com.accenture.academico.repository.ContaBancariaRepository;
import com.accenture.academico.repository.OperacaoRepository;
import com.accenture.academico.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaBancariaService {

    @Autowired
    ContaBancariaRepository contaBancariaRepository;

    @Autowired
    OperacaoRepository operacaoRepository;

    @Autowired
    TransferenciaRepository transferenciaRepository;

    public Optional<ContaBancaria> buscarContaPorId(Integer id) {
        return contaBancariaRepository.findById(id);
    }

    public BigDecimal consultarSaldo(Integer id) {
        return buscarContaPorId(id)
                .map(ContaBancaria::getSaldo)
                .orElse(BigDecimal.ZERO);
    }

    public void realizarDeposito(Integer id, BigDecimal valor) {
        ContaBancaria conta = buscarContaPorId(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.setSaldo(conta.getSaldo().add(valor));
        contaBancariaRepository.save(conta);

        Operacao operacao = new Operacao(TipoOperacao.DEPOSITO, BigDecimal.ZERO, conta);
        operacaoRepository.save(operacao);
    }

    public void realizarSaque(Integer id, BigDecimal valor) {
        ContaBancaria conta = buscarContaPorId(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaBancariaRepository.save(conta);

        Operacao operacao = new Operacao(TipoOperacao.SAQUE, BigDecimal.ZERO, conta);
        operacaoRepository.save(operacao);
    }

    public void realizarTransferencia(TransferenciaDTO transferenciaDTO) {
        ContaBancaria contaOrigem = buscarContaPorId(transferenciaDTO.getContaOrigemId())
                .orElseThrow(() -> new RuntimeException("Conta origem não encontrada"));
        ContaBancaria contaDestino = buscarContaPorId(transferenciaDTO.getContaDestinoId())
                .orElseThrow(() -> new RuntimeException("Conta destino não encontrada"));

        if (contaOrigem.getSaldo().compareTo(transferenciaDTO.getValor()) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(transferenciaDTO.getValor()));
        contaDestino.setSaldo(contaDestino.getSaldo().add(transferenciaDTO.getValor()));

        contaBancariaRepository.save(contaOrigem);
        contaBancariaRepository.save(contaDestino);

        Operacao operacao = new Operacao(TipoOperacao.TRANSFERENCIA, BigDecimal.ZERO, contaOrigem);
        operacaoRepository.save(operacao);

        Transferencia transferencia = new Transferencia(operacao, contaDestino);
        transferenciaRepository.save(transferencia);
    }

    public List<ContaBancaria> buscarContasPorCliente(Integer clienteId) {
        return contaBancariaRepository.findByClienteId(clienteId);
    }
}
