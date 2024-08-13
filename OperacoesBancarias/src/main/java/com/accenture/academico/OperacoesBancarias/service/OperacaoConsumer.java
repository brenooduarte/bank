package com.accenture.academico.OperacoesBancarias.service;

import com.accenture.academico.OperacoesBancarias.model.MensagemOperacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OperacaoConsumer {

    @Autowired
    OperacaoService operacaoService;

    @KafkaListener(topics = "operacao.request.topic.v1", groupId = "operacoes-group")
    public void consume(String mensagem) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MensagemOperacao operacao = objectMapper.readValue(mensagem, MensagemOperacao.class);

            switch (operacao.tipoOperacao()) {
                case DEPOSITO -> operacaoService.realizarDeposito(operacao);
                case SAQUE -> operacaoService.realizarSaque(operacao);
                case TRANSFERENCIA -> operacaoService.realizarTransferencia(operacao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
