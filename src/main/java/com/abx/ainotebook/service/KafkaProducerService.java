package com.abx.ainotebook.service;

import com.abx.ainotebook.model.ImmutableMouseClick;
import com.abx.ainotebook.model.MouseClick;
import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    public static final String TOPIC_MOUSE_CLICK = "topic-mouse-click";

    private final KafkaTemplate<UUID, MouseClick> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<UUID, MouseClick> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void recordMouseClick(UUID userId, UUID notebookId, long coordX, long coordY, String targetId) {
        MouseClick mouseClick = ImmutableMouseClick.builder()
                .x(coordX)
                .y(coordY)
                .target(targetId)
                .notebookId(notebookId)
                .build();
        kafkaTemplate.send(TOPIC_MOUSE_CLICK, userId, mouseClick);
    }
}
