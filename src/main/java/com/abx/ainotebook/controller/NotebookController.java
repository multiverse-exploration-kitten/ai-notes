package com.abx.ainotebook.controller;

import com.abx.ainotebook.model.MouseClick;
import com.abx.ainotebook.service.KafkaProducerService;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotebookController {
    private final KafkaProducerService kafkaProducerService;

    public NotebookController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/track-mouse-click/{notebookId}")
    public void trackMouseClick(@PathVariable UUID notebookId, @RequestBody MouseClick mouseClick) {
        kafkaProducerService.recordMouseClick(
                notebookId, mouseClick.getX(), mouseClick.getY(), mouseClick.getClickedTarget());
    }
}
