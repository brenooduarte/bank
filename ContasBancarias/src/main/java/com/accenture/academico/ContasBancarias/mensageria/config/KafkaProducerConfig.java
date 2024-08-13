package com.accenture.academico.ContasBancarias.mensageria.config;

import com.accenture.academico.ContasBancarias.domain.model.MensagemOperacao;
import com.accenture.academico.ContasBancarias.domain.model.ValidacaoClienteEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${topicos.operacao.request.topic}")
    private String operacaoRequestTopic;

    @Value("${topicos.validacao.cliente.request.topic}")
    private String validacaoClienteRequestTopic;

//    @Value("${topicos.validacao.cliente.resultado.topic}")
//    private String resultadoValidacaoClienteTopic;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<String, MensagemOperacao> kafkaTemplateOperacao() {
        return new KafkaTemplate<>(producerFactory(MensagemOperacao.class));
    }

    @Bean
    public KafkaTemplate<String, ValidacaoClienteEvent> kafkaTemplateValidacaoCliente() {
        return new KafkaTemplate<>(producerFactory(ValidacaoClienteEvent.class));
    }

//    @Bean
//    public KafkaTemplate<String, ResultadoValidacaoClienteEvent> kafkaTemplateResultadoValidacao() {
//        return new KafkaTemplate<>(producerFactory(ResultadoValidacaoClienteEvent.class));
//    }

    @Bean
    public ProducerFactory<String, MensagemOperacao> producerFactoryMensagemOperacao() {
        return producerFactory(MensagemOperacao.class);
    }

    @Bean
    public ProducerFactory<String, ValidacaoClienteEvent> producerFactoryValidacaoCliente() {
        return producerFactory(ValidacaoClienteEvent.class);
    }

//    @Bean
//    public ProducerFactory<String, ResultadoValidacaoClienteEvent> producerFactoryResultadoValidacao() {
//        return producerFactory(ResultadoValidacaoClienteEvent.class);
//    }

    private <T> ProducerFactory<String, T> producerFactory(Class<T> valueType) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public NewTopic operacaoRequestTopic() {
        return TopicBuilder
                .name(operacaoRequestTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic validacaoClienteRequestTopic() {
        return TopicBuilder
                .name(validacaoClienteRequestTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }

//    @Bean
//    public NewTopic resultadoValidacaoClienteTopic() {
//        return TopicBuilder
//                .name(resultadoValidacaoClienteTopic)
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
}
