package com.abx.ainotebook.service;

import com.abx.ainotebook.dto.ImmutableUserEventDto;
import com.abx.ainotebook.dto.UserEventDto;
import com.abx.ainotebook.model.Keystroke;
import com.abx.ainotebook.model.MouseClick;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    public static final String TOPIC_USER_EVENT = "topic-user-event";

    private final KafkaTemplate<UUID, UserEventDto> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<UUID, UserEventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Optional<UserEventDto> recordMouseClick(UUID userId, UUID noteId, MouseClick mouseClick) {
        Map<String, Object> mouseClickAttributes = new HashMap<>();
        mouseClickAttributes.put("x", mouseClick.getX());
        mouseClickAttributes.put("y", mouseClick.getY());
        mouseClickAttributes.put("clickedTarget", mouseClick.getClickedTarget());

        UserEventDto userEventDto = ImmutableUserEventDto.builder()
                .userId(userId)
                .noteId(noteId)
                .eventType("MouseClick")
                .eventAttributes(mouseClickAttributes)
                .build();

        var future = kafkaTemplate.send(TOPIC_USER_EVENT, noteId, userEventDto);
        return future.thenApply(result -> Optional.of(userEventDto))
                .exceptionally(ex -> Optional.empty())
                .join();
    }

    public Optional<UserEventDto> recordKeystroke(UUID userId, UUID noteId, Keystroke keystroke) {
        Map<String, Object> keystrokeAttributes = new HashMap<>();
        keystrokeAttributes.put("pressedKey", keystroke.getPressedKey());

        UserEventDto userEventDto = ImmutableUserEventDto.builder()
                .userId(userId)
                .noteId(noteId)
                .eventType("Keystroke")
                .eventAttributes(keystrokeAttributes)
                .build();

        var future = kafkaTemplate.send(TOPIC_USER_EVENT, noteId, userEventDto);
        return future.thenApply(result -> Optional.of(userEventDto))
                .exceptionally(ex -> Optional.empty())
                .join();
    }
}
