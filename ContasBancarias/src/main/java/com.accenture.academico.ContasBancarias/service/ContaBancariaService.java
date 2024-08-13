package com.accenture.academico.ContasBancarias.service;

import com.accenture.academico.ContasBancarias.model.ContaBancaria;
import com.accenture.academico.ContasBancarias.model.MensagemOperacao;
import com.accenture.academico.ContasBancarias.model.ResultadoValidacaoClienteEvent;
import com.accenture.academico.ContasBancarias.model.ValidacaoClienteEvent;
import com.accenture.academico.ContasBancarias.model.dto.form.ContaBancariaDTOForm;
import com.accenture.academico.ContasBancarias.model.enums.StatusConta;
import com.accenture.academico.ContasBancarias.producer.ContaRequestProducer;
import com.accenture.academico.ContasBancarias.repository.ContaBancariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ContaBancariaService {

    @Autowired
    ContaBancariaRepository contaBancariaRepository;

    @Autowired
    ContaRequestProducer contaRequestProducer;

    public Optional<ContaBancaria> buscarContaPorId(Integer id) {
        return contaBancariaRepository.findById(id);
    }

    public BigDecimal consultarSaldo(Integer id) {
        return buscarContaPorId(id)
                .map(ContaBancaria::getSaldo)
                .orElse(BigDecimal.ZERO);
    }

    @Transactional
    public void solicitarDeposito(MensagemOperacao mensagem) {
        ContaBancaria contaOrigem = contaBancariaRepository.findById(mensagem.idContaOrigem())
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

        contaOrigem.setSaldo(contaOrigem.getSaldo().add(mensagem.valor()));
        contaBancariaRepository.save(contaOrigem);

        contaRequestProducer.enviarOperacao(mensagem);
    }

    @Transactional
    public void solicitarSaque(MensagemOperacao mensagem) {
        ContaBancaria contaOrigem = contaBancariaRepository.findById(mensagem.idContaOrigem())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));

        if (contaOrigem.getSaldo().compareTo(mensagem.valor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para saque");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(mensagem.valor()));
        contaBancariaRepository.save(contaOrigem);

        contaRequestProducer.enviarOperacao(mensagem);
    }

    @Transactional
    public void solicitarTransferencia(MensagemOperacao mensagem) {
        ContaBancaria contaOrigem = contaBancariaRepository.findById(mensagem.idContaOrigem())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));

        ContaBancaria contaDestino = contaBancariaRepository.findById(mensagem.idContaDestino())
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

        if (contaOrigem.getSaldo().compareTo(mensagem.valor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para transferência");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(mensagem.valor()));
        contaDestino.setSaldo(contaDestino.getSaldo().add(mensagem.valor()));

        contaBancariaRepository.save(contaOrigem);
        contaBancariaRepository.save(contaDestino);

        contaRequestProducer.enviarOperacao(mensagem);
    }

    @Transactional
    public void criarConta(ContaBancariaDTOForm contaBancariaDTOForm) {
        ContaBancaria novaConta = new ContaBancaria(
                BigDecimal.ZERO,
                contaBancariaDTOForm.tipoConta(),
                contaBancariaDTOForm.idAgencia(),
                contaBancariaDTOForm.idCliente()
        );

        ContaBancaria contaSalva = contaBancariaRepository.save(novaConta);

        ValidacaoClienteEvent eventoValidacao = new ValidacaoClienteEvent(
                contaSalva.getIdContaBancaria(),
                contaBancariaDTOForm.idCliente()
        );

        contaRequestProducer.enviarValidacaoCliente(eventoValidacao);
    }

    public void atualizarStatusConta(Integer idContaBancaria, StatusConta statusConta) {
        Optional<ContaBancaria> contaEncontrada = contaBancariaRepository.findById(idContaBancaria);
        contaEncontrada.ifPresent(
                contaBancaria -> contaBancaria.setStatusConta(statusConta)
        );

        contaBancariaRepository.save(contaEncontrada.get());
    }

}
