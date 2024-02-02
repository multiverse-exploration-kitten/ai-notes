package com.abx.ainotebook.controller;

import com.abx.ainotebook.model.Keystroke;
import com.abx.ainotebook.model.MouseClick;
import com.abx.ainotebook.service.KafkaProducerService;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackingController {
    private final KafkaProducerService kafkaProducerService;

    public TrackingController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/track-mouse-click/{userId}/{noteId}")
    public void trackMouseClick(
            @PathVariable UUID userId, @PathVariable UUID noteId, @RequestBody MouseClick mouseClick) {
        kafkaProducerService.recordMouseClick(userId, noteId, mouseClick);
    }

    @PostMapping("/track-keystroke/{userId}/{noteId}")
    public void trackKeystroke(@PathVariable UUID userId, @PathVariable UUID noteId, @RequestBody String pressedKey) {
        kafkaProducerService.recordKeystroke(userId, noteId, pressedKey);
    }
}
