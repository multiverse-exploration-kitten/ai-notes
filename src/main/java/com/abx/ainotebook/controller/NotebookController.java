package com.abx.ainotebook.controller;

import com.abx.ainotebook.model.MouseClick;
import com.abx.ainotebook.service.KafkaProducerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class NotebookController {
    private final KafkaProducerService kafkaProducerService;

    public NotebookController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/track-mouse-click/{NotebookId}")
    public void trackMouseClick(@PathVariable UUID NotebookId, @RequestBody MouseClick mouseClick) {
        kafkaProducerService.recordMouseClick(NotebookId, mouseClick.getX(), mouseClick.getY(), mouseClick.getClickedTarget());
    }
}
