package com.abx.ainotebook.service;

import com.abx.ainotebook.dto.UserEventDto;
import com.abx.ainotebook.model.UserEventMongoModel;
import com.abx.ainotebook.repository.UserEventRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    public static final String TOPIC_USER_EVENT = "topic-user-event";

    private final UserEventRepository userEventRepository;

    public KafkaConsumerService(UserEventRepository userEventRepository) {
        this.userEventRepository = userEventRepository;
    }

    @KafkaListener(topics = TOPIC_USER_EVENT, groupId = "group-user-event")
    public void listenToPartition(@Payload UserEventDto userEventDto) {
        UserEventMongoModel model = new UserEventMongoModel();

        model.setId(UUID.randomUUID());
        model.setUserId(userEventDto.getUserId());
        model.setNoteId(userEventDto.getNoteId());
        model.setEventType(userEventDto.getEventType());
        model.setEventAttributes(userEventDto.getEventAttributes());
        model.setTimestamp(Instant.now());

        userEventRepository.save(model);
    }

    @Scheduled(fixedRate = 5000)
    public void pollKafka() {}
}
