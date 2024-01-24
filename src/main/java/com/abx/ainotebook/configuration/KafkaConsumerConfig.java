package com.abx.ainotebook.configuration;

import com.abx.ainotebook.model.MouseClick;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<UUID, MouseClick> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-trace"); // identify consumer group
        return new DefaultKafkaConsumerFactory<>(
                props, new UUIDDeserializer(), new JsonDeserializer<>(MouseClick.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<UUID, MouseClick> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<UUID, MouseClick> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
