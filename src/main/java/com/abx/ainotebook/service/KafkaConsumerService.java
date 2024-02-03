package com.abx.ainotebook.service;

import com.abx.ainotebook.dto.UserEventDto;
import com.abx.ainotebook.model.ActionFilter;
import com.abx.ainotebook.model.UserEventMongoModel;
import com.abx.ainotebook.repository.UserEventRepository;

import java.util.Set;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    public static final String TOPIC_USER_EVENT = "topic-user-event";
    private final UserEventRepository userEventRepository;
    private final ActionFilter actionFilter;

    public KafkaConsumerService(UserEventRepository userEventRepository) {
        this.userEventRepository = userEventRepository;
        this.actionFilter = new ActionFilter();
    }

    @KafkaListener(topics = TOPIC_USER_EVENT, groupId = "group-user-event")
    public void listenToPartition(@Payload UserEventDto userEventDto) {
        UserEventMongoModel model = new UserEventMongoModel();

        long eventTimestamp = System.currentTimeMillis();
        UUID userId = userEventDto.getUserId();
        String eventType = userEventDto.getEventType();

        model.setId(UUID.randomUUID());
        model.setUserId(userId);
        model.setNoteId(userEventDto.getNoteId());
        model.setEventType(eventType);
        model.setEventAttributes(userEventDto.getEventAttributes());
        model.setTimestamp(eventTimestamp);
        userEventRepository.save(model);

        if (eventType.equalsIgnoreCase("Keystroke")) {
            actionFilter.processKeyPressEvent(userId, eventTimestamp);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void genInsight() {
        Set<UUID> inactiveUsers = actionFilter.checkInactiveUsers();

        // TODO: hand the user set to GenInsight Service
    }
}
