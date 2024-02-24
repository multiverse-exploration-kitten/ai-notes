// package com.abx.ainotebook;
//
// import com.abx.ainotebook.dto.ImmutableUserEventDto;
// import com.abx.ainotebook.dto.UserEventDto;
// import com.abx.ainotebook.model.ImmutableMouseClick;
// import com.abx.ainotebook.model.MouseClick;
// import com.abx.ainotebook.repository.UserEventRepository;
// import com.abx.ainotebook.service.KafkaConsumerService;
// import com.abx.ainotebook.service.KafkaProducerService;
// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.ArgumentCaptor;
// import org.mockito.Captor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.kafka.test.context.EmbeddedKafka;
// import org.springframework.test.annotation.DirtiesContext;
//
// import java.util.HashMap;
// import java.util.Map;
// import java.util.UUID;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.Mockito.timeout;
// import static org.mockito.Mockito.verify;
//
// @SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
// @EmbeddedKafka
// public class TrackingServiceTest {
//    @Autowired
//    private KafkaProducerService kafkaProducerService;
//
//    @Autowired
//    private KafkaConsumerService kafkaConsumerService;
//
//    @Captor
//    ArgumentCaptor<UserEventDto> userEventDtoArgumentCaptor;
//
//    private UUID userId;
//
//    private UUID noteId;
//
//    @BeforeEach
//    void before() {
//        userId = UUID.randomUUID();
//        noteId = UUID.randomUUID();
//    }
//
//    @Test
//    public void TrackingService_RecordMouseClick_ListenerReceivesUserEvent() {
//        MouseClick mouseClick = ImmutableMouseClick.builder()
//                .x(16)
//                .y(15)
//                .clickedTarget("GenInsight")
//                .build();
//
//        kafkaProducerService.recordMouseClick(userId, noteId, mouseClick);
//        verify(kafkaConsumerService, timeout(5000).times(1))
//                .listenToPartition(userEventDtoArgumentCaptor.capture());
//
//        UserEventDto userEventDto = userEventDtoArgumentCaptor.getValue();
//        assertNotNull(userEventDto);
//        assertEquals(userId, userEventDto.getUserId());
//        assertEquals(noteId, userEventDto.getNoteId());
//        assertEquals("MouseClick", userEventDto.getEventType());
//        assertEquals(16, userEventDto.getEventAttributes().get("x"));
//        assertEquals(15, userEventDto.getEventAttributes().get("y"));
//        assertEquals("GenInsight", userEventDto.getEventAttributes().get("clickedTarget"));
//    }
// }
