package com.accenture.academico.Clientes.producer;

import com.accenture.academico.Clientes.model.ResultadoValidacaoClienteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClienteRequestProducer {

    @Value("${topicos.validacao.cliente.resultado.topic}")
    private String resultadoValidacaoClienteTopic;

    @Autowired
    KafkaTemplate<String, ResultadoValidacaoClienteEvent> kafkaTemplateResultadoValidacao;

    public void enviarResultadoValidacaoCliente(ResultadoValidacaoClienteEvent eventoResultadoValidacao) {
        try {
            kafkaTemplateResultadoValidacao.send(resultadoValidacaoClienteTopic, eventoResultadoValidacao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}