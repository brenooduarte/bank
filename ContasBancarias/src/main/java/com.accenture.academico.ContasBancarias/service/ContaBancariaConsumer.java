package com.accenture.academico.ContasBancarias.service;

import com.accenture.academico.ContasBancarias.model.ResultadoValidacaoClienteEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ContaBancariaConsumer {

    @Autowired
    ContaBancariaService contaBancariaService;

    @KafkaListener(topics = "validacao.cliente.response.topic.v1", groupId = "clientes-group")
    public void consume(String mensagem) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            var resultadoValidacao = objectMapper.readValue(mensagem, ResultadoValidacaoClienteEvent.class);

            contaBancariaService.atualizarStatusConta(resultadoValidacao.idContaBancaria(), resultadoValidacao.statusConta());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

