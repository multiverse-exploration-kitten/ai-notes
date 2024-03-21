package com.abx.ainotebook.service;

import com.abx.ainotebook.dto.ImmutableUserEventDto;
import com.abx.ainotebook.dto.UserEventDto;
import com.abx.ainotebook.model.Keystroke;
import com.abx.ainotebook.model.MouseClick;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    public static final String TOPIC_MOUSE_CLICK = "topic-mouse-click";

    public static final String TOPIC_KEYSTROKE = "topic-keystroke";

    private final KafkaTemplate<UUID, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<UUID, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void recordMouseClick(UUID userId, UUID noteId, MouseClick mouseClick) {
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

        kafkaTemplate.send(TOPIC_MOUSE_CLICK, noteId, userEventDto);
    }

    public void recordKeystroke(UUID userId, UUID noteId, String pressedKey) {
        Map<String, Object> mouseClickAttributes = new HashMap<>();
        mouseClickAttributes.put("pressedKey", pressedKey);

        UserEventDto userEventDto = ImmutableUserEventDto.builder()
                .userId(userId)
                .noteId(noteId)
                .eventType("Keystroke")
                .eventAttributes(mouseClickAttributes)
                .build();

        kafkaTemplate.send(TOPIC_KEYSTROKE, noteId, userEventDto);
    }
}
