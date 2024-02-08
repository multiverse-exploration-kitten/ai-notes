package com.abx.ainotebook.controller;

import com.abx.ainotebook.service.InsightService;
import java.util.Objects;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenInsightController {
    private final InsightService insightService;

    public GenInsightController(InsightService insightService) {
        this.insightService = insightService;
    }

    @GetMapping("/note/{noteId}/genInsight/{notes}")
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

    @GetMapping("/note/{noteId}/genSummary/{notes}")
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
