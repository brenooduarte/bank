package com.accenture.academico.Clientes.mensageria.config;

import com.accenture.academico.Clientes.mensageria.model.ResultadoValidacaoClienteEvent;
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

    @Value("${topicos.validacao.cliente.resultado.topic}")
    private String resultadoValidacaoClienteTopic;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<String, ResultadoValidacaoClienteEvent> kafkaTemplateResultadoValidacao() {
        return new KafkaTemplate<>(producerFactory(ResultadoValidacaoClienteEvent.class));
    }

    @Bean
    public ProducerFactory<String, ResultadoValidacaoClienteEvent> producerFactoryResultadoValidacao() {
        return producerFactory(ResultadoValidacaoClienteEvent.class);
    }

    private <T> ProducerFactory<String, T> producerFactory(Class<T> valueType) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public NewTopic resultadoValidacaoClienteTopic() {
        return TopicBuilder
                .name(resultadoValidacaoClienteTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
