package com.abx.ainotebook.service;

import com.abx.ainotebook.model.Keystroke;
import com.abx.ainotebook.model.KeystrokeMongoModel;
import com.abx.ainotebook.model.MouseClick;
import com.abx.ainotebook.model.MouseClickMongoModel;
import com.abx.ainotebook.repository.KeystrokeRepository;
import com.abx.ainotebook.repository.MouseClickRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final MouseClickRepository mouseClickRepository;

    private final KeystrokeRepository keystrokeRepository;

    public static final String TOPIC_KEYSTROKE = "topic-keystroke";

    public static final String TOPIC_MOUSE_CLICK = "topic-mouse-click";

    public KafkaConsumerService(MouseClickRepository mouseClickRepository, KeystrokeRepository keystrokeRepository) {
        this.mouseClickRepository = mouseClickRepository;
        this.keystrokeRepository = keystrokeRepository;
    }

    @KafkaListener(topics = TOPIC_MOUSE_CLICK, groupId = "group-mouse-click")
    public void listenToPartition(@Header(KafkaHeaders.RECEIVED_KEY) UUID notebookId, @Payload MouseClick mouseClick) {
        MouseClickMongoModel model = new MouseClickMongoModel();

        model.setId(UUID.randomUUID());
        model.setNotebookId(notebookId);
        model.setCoordX(mouseClick.getX());
        model.setCoordY(mouseClick.getY());
        model.setClickedTargetId(mouseClick.getClickedTarget());
        model.setTimestamp(Instant.now());
        mouseClickRepository.save(model);
    }

    @KafkaListener(topics = TOPIC_KEYSTROKE, groupId = "group-keystroke")
    public void listenToPartition(@Header(KafkaHeaders.RECEIVED_KEY) UUID notebookId, @Payload Keystroke keystroke) {
        KeystrokeMongoModel model = new KeystrokeMongoModel();

        model.setId(UUID.randomUUID());
        model.setNotebookId(notebookId);
        model.setPressedKey(keystroke.getPressedKey());
        model.setKeystrokeType(keystroke.getKeystrokeType());
        model.setTimestamp(Instant.now());
        keystrokeRepository.save(model);
    }
}
