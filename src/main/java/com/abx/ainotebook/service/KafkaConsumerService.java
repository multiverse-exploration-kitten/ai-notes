package com.abx.ainotebook.service;

import com.abx.ainotebook.model.MouseClick;
import com.abx.ainotebook.model.MouseClickMongoModel;
import com.abx.ainotebook.repository.MouseClickRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final MouseClickRepository mouseClickRepository;

    public static final String TOPIC_MOUSE_CLICK = "topic-mouse-click";

    public KafkaConsumerService(MouseClickRepository mouseClickRepository) {
        this.mouseClickRepository = mouseClickRepository;
    }

    @KafkaListener(topics = TOPIC_MOUSE_CLICK, groupId = "group-mouse-click")
    public void listenToPartition(@Payload MouseClick mouseClick) {
        MouseClickMongoModel model = new MouseClickMongoModel();

        model.setId(UUID.randomUUID());
        model.setNotebookId(mouseClick.getNotebookId());
        model.setCoordX(mouseClick.getX());
        model.setCoordY(mouseClick.getY());
        model.setClickedTargetId(mouseClick.getClickedTarget());
        model.setTimestamp(Instant.now());
        mouseClickRepository.save(model);
    }
}
