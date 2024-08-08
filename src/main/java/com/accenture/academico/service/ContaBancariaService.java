package com.accenture.academico.service;

import com.accenture.academico.model.ContaBancaria;
import com.accenture.academico.model.Operacao;
import com.accenture.academico.model.dto.TransferenciaDTO;
import com.accenture.academico.repository.ClienteRepository;
import com.accenture.academico.repository.ContaBancariaRepository;
import com.accenture.academico.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OperacaoRepository operacaoRepository;

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

        // Logar a operação
        Operacao operacao = new Operacao();
        operacao.setDataHoraMovimento(java.time.LocalDateTime.now());
        operacao.setTipoOperacao(com.accenture.academico.model.enums.TipoOperacao.DEPOSITO);
        operacao.setTaxaOperacao(BigDecimal.ZERO); // Defina a taxa conforme necessário
        operacao.setContaOrigem(conta);
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

        // Logar a operação
        Operacao operacao = new Operacao();
        operacao.setDataHoraMovimento(java.time.LocalDateTime.now());
        operacao.setTipoOperacao(com.accenture.academico.model.enums.TipoOperacao.SAQUE);
        operacao.setTaxaOperacao(BigDecimal.ZERO); // Defina a taxa conforme necessário
        operacao.setContaOrigem(conta);
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

        // Logar a operação na conta origem
        Operacao operacaoOrigem = new Operacao();
        operacaoOrigem.setDataHoraMovimento(java.time.LocalDateTime.now());
        operacaoOrigem.setTipoOperacao(com.accenture.academico.model.enums.TipoOperacao.TRANSFERENCIA);
        operacaoOrigem.setTaxaOperacao(BigDecimal.ZERO); // Defina a taxa conforme necessário
        operacaoOrigem.setContaOrigem(contaOrigem);
        operacaoRepository.save(operacaoOrigem);

        // Logar a operação na conta destino
        Operacao operacaoDestino = new Operacao();
        operacaoDestino.setDataHoraMovimento(java.time.LocalDateTime.now());
        operacaoDestino.setTipoOperacao(com.accenture.academico.model.enums.TipoOperacao.TRANSFERENCIA);
        operacaoDestino.setTaxaOperacao(BigDecimal.ZERO); // Defina a taxa conforme necessário
        operacaoDestino.setContaOrigem(contaDestino);
        operacaoRepository.save(operacaoDestino);
    }

    public List<ContaBancaria> buscarContasPorCliente(Integer clienteId) {
        return contaBancariaRepository.findByClienteId(clienteId);
//        return List.of(new ContaBancaria());
    }
}
