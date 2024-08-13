package com.accenture.academico.Clientes.service;

import com.accenture.academico.Clientes.model.Cliente;
import com.accenture.academico.Clientes.model.ResultadoValidacaoClienteEvent;
import com.accenture.academico.Clientes.model.ValidacaoClienteEvent;
import com.accenture.academico.Clientes.model.enums.StatusConta;
import com.accenture.academico.Clientes.producer.ClienteRequestProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteConsumer {
    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRequestProducer clienteRequestProducer;

    @KafkaListener(topics = "validacao.cliente.request.topic.v1", groupId = "clientes-group")
    public void consume(String mensagem) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ValidacaoClienteEvent validacaoClienteEvent = objectMapper.readValue(mensagem, ValidacaoClienteEvent.class);

            Optional<Cliente> clienteValido = clienteService.buscarClientePorId(validacaoClienteEvent.idCliente());
            ResultadoValidacaoClienteEvent resultado;

            if (clienteValido.isPresent()) {
                resultado = new ResultadoValidacaoClienteEvent(validacaoClienteEvent.idContaBancaria(), StatusConta.ATIVA);
            } else {
                resultado = new ResultadoValidacaoClienteEvent(validacaoClienteEvent.idContaBancaria(), StatusConta.INATIVA);
            }
            clienteRequestProducer.enviarResultadoValidacaoCliente(resultado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

