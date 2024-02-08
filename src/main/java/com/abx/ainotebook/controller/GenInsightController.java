package com.abx.ainotebook.controller;

import com.abx.ainotebook.service.InsightService;
import com.abx.ainotebook.service.NoteBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;

@RestController
public class GenInsightController {
    private final InsightService insightService;

    public GenInsightController(InsightService insightService) {
        this.insightService = insightService;
    }

    @GetMapping("/notes/{noteId}/{notes}")
    public ResponseEntity<String> genInsight(@PathVariable("noteId") UUID noteId, @PathVariable("notes") String notes) {
        if (Objects.equals(notes, "")) {
            return ResponseEntity.badRequest().body(null);
        }
        if (Objects.equals(noteId, null)) {
            return ResponseEntity.badRequest().body(null);
        }
        var insights = insightService.genInsight(notes);
        return ResponseEntity.ok(insights);
    }

    @GetMapping("/notes/{noteId}/{notes}")
    public ResponseEntity<String> genSummary(@PathVariable("noteId") UUID noteId, @PathVariable("notes") String notes) {
        if (Objects.equals(notes, "")) {
            return ResponseEntity.badRequest().body(null);
        }
        if (Objects.equals(noteId, null)) {
            return ResponseEntity.badRequest().body(null);
        }
        var insights = insightService.genSummary(notes);
        return ResponseEntity.ok(insights);
    }
}
