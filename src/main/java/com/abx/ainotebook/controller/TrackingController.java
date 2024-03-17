package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.UserEventDto;
import com.abx.ainotebook.model.Keystroke;
import com.abx.ainotebook.model.MouseClick;
import com.abx.ainotebook.service.KafkaProducerService;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserEventDto> trackMouseClick(
            @PathVariable UUID userId, @PathVariable UUID noteId, @RequestBody MouseClick mouseClick) {
        Optional<UserEventDto> optionalUserEventDto = kafkaProducerService.recordMouseClick(userId, noteId, mouseClick);
        return optionalUserEventDto
                .map(userEventDto -> ResponseEntity.ok(userEventDto))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @PostMapping("/track-keystroke/{userId}/{noteId}")
    public ResponseEntity<UserEventDto> trackKeystroke(
            @PathVariable UUID userId, @PathVariable UUID noteId, @RequestBody Keystroke keystroke) {
        Optional<UserEventDto> optionalUserEventDto = kafkaProducerService.recordKeystroke(userId, noteId, keystroke);
        return optionalUserEventDto
                .map(userEventDto -> ResponseEntity.ok(userEventDto))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }
}
