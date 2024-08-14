package com.accenture.academico.ContasBancarias.mensageria.producer;

import com.accenture.academico.ContasBancarias.mensageria.model.MensagemOperacao;
import com.accenture.academico.ContasBancarias.mensageria.model.ValidacaoClienteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContaRequestProducer {
    @Value("${topicos.operacao.request.topic}")
    private String operacaoRequestTopic;

    @Value("${topicos.validacao.cliente.request.topic}")
    private String validacaoClienteRequestTopic;

    @Autowired
    KafkaTemplate<String, MensagemOperacao> kafkaTemplateOperacao;

    @Autowired
    KafkaTemplate<String, ValidacaoClienteEvent> kafkaTemplateValidacaoCliente;

    public void enviarOperacao(MensagemOperacao mensagemOperacao) {
        try {
            kafkaTemplateOperacao.send(operacaoRequestTopic, mensagemOperacao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void enviarValidacaoCliente(ValidacaoClienteEvent eventoValidacao) {
        try {
            kafkaTemplateValidacaoCliente.send(validacaoClienteRequestTopic, eventoValidacao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
