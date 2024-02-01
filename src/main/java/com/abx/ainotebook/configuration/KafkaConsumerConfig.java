package com.abx.ainotebook.configuration;

import com.abx.ainotebook.dto.UserEventDto;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

    @Value("${spring.kafka.consumer.properties.spring.json.trusted.packages}")
    private String trustedPackages;

    @Bean
    public ConsumerFactory<UUID, UserEventDto> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-user-event"); // identify consumer group

        JsonDeserializer<UserEventDto> userEventDtoJsonDeserializer = new JsonDeserializer<>();
        userEventDtoJsonDeserializer.addTrustedPackages(trustedPackages);
        return new DefaultKafkaConsumerFactory<>(
                props, new UUIDDeserializer(), new JsonDeserializer<>(UserEventDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<UUID, UserEventDto> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<UUID, UserEventDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}