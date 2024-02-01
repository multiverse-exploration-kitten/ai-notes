package com.abx.ainotebook.service;

import com.abx.ainotebook.model.Keystroke;
import com.abx.ainotebook.model.MouseClick;
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

    public void recordMouseClick(UUID notebookId, MouseClick mouseClick) {
        kafkaTemplate.send(TOPIC_MOUSE_CLICK, notebookId, mouseClick);
    }

    public void recordKeystroke(UUID notebookId, Keystroke keystroke) {
        kafkaTemplate.send(TOPIC_KEYSTROKE, notebookId, keystroke);
    }
}
