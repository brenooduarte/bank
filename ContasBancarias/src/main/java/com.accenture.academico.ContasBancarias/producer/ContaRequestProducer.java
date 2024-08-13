package com.accenture.academico.ContasBancarias.producer;

import com.accenture.academico.ContasBancarias.model.MensagemOperacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContaRequestProducer {
    @Value("${topicos.operacao.request.topic}")
    private String operacaoRequestTopic;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, MensagemOperacao> kafkaTemplate;

    public void enviarOperacao(MensagemOperacao mensagemOperacao) {
        try {
            kafkaTemplate.send(operacaoRequestTopic, mensagemOperacao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
