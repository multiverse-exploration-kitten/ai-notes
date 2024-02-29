 package com.abx.ainotebook;

 import com.abx.ainotebook.dto.ImmutableUserEventDto;
 import com.abx.ainotebook.dto.UserEventDto;
 import com.abx.ainotebook.model.ImmutableMouseClick;
 import com.abx.ainotebook.model.MouseClick;
 import com.abx.ainotebook.service.KafkaConsumerService;
 import com.fasterxml.jackson.core.JsonProcessingException;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.UUID;
 import org.apache.kafka.clients.producer.Producer;
 import org.apache.kafka.clients.producer.ProducerRecord;
 import org.apache.kafka.common.serialization.StringSerializer;
 import org.apache.kafka.common.serialization.UUIDSerializer;
 import org.junit.jupiter.api.AfterAll;
 import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.TestInstance;
 import org.mockito.ArgumentCaptor;
 import org.mockito.Captor;
 import org.mockito.Mockito;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.boot.test.mock.mockito.MockBean;
 import org.springframework.boot.test.mock.mockito.SpyBean;
 import org.springframework.kafka.core.DefaultKafkaProducerFactory;
 import org.springframework.kafka.support.serializer.JsonSerializer;
 import org.springframework.kafka.test.EmbeddedKafkaBroker;
 import org.springframework.kafka.test.context.EmbeddedKafka;
 import org.springframework.kafka.test.utils.KafkaTestUtils;
 import org.springframework.test.context.TestPropertySource;

 @SpringBootTest
 @EmbeddedKafka
 @TestPropertySource(
        properties = {
            "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
            "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
        })
 @TestInstance(TestInstance.Lifecycle.PER_CLASS)
 public class TrackingServiceTest {
    private Producer<UUID, UserEventDto> producer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @SpyBean
    private KafkaConsumerService kafkaConsumerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Captor
    private ArgumentCaptor<UserEventDto> userEventDtoArgumentCaptor;

    private UUID userId;

    private UUID noteId;

    @BeforeEach
    void before() {
        userId = UUID.randomUUID();
        noteId = UUID.randomUUID();

        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        JsonSerializer<UserEventDto> userEventJsonSerializer = new JsonSerializer<>();
        producer = new DefaultKafkaProducerFactory<>(
                configs,
                new UUIDSerializer(),
                userEventJsonSerializer
        ).createProducer();
    }

    @AfterAll
    void after() {
        producer.close();
    }

    @Test
    public void kafkaConsumerService_ListenToPartition_ReceivesProducedMouseclick() throws JsonProcessingException {
        MouseClick mouseClick = ImmutableMouseClick.builder()
                .x(16)
                .y(15)
                .clickedTarget("GenInsight")
                .build();

        Map<String, Object> mouseClickAttributes = new HashMap<>();
        mouseClickAttributes.put("x", mouseClick.getX());
        mouseClickAttributes.put("y", mouseClick.getY());
        mouseClickAttributes.put("clickedTarget", mouseClick.getClickedTarget());

        UserEventDto producedUserEventDto = ImmutableUserEventDto.builder()
                .userId(userId)
                .noteId(noteId)
                .eventType("MouseClick")
                .eventAttributes(mouseClickAttributes)
                .build();

        producer.send(new ProducerRecord<>("topic-user-event", 0, noteId, producedUserEventDto));
        Mockito.verify(kafkaConsumerService, Mockito.timeout(15000).times(1))
                .listenToPartition(userEventDtoArgumentCaptor.capture());

        UserEventDto userEventDto = userEventDtoArgumentCaptor.getValue();
        Assertions.assertNotNull(userEventDto);
        Assertions.assertEquals(userId, userEventDto.getUserId());
        Assertions.assertEquals(noteId, userEventDto.getNoteId());
        Assertions.assertEquals("MouseClick", userEventDto.getEventType());
        Assertions.assertEquals(16, userEventDto.getEventAttributes().get("x"));
        Assertions.assertEquals(15, userEventDto.getEventAttributes().get("y"));
        Assertions.assertEquals("GenInsight", userEventDto.getEventAttributes().get("clickedTarget"));
    }
 }
