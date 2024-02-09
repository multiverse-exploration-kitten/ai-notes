package com.abx.ainotebook.controller;

import com.abx.ainotebook.model.Note;
import com.abx.ainotebook.service.InsightService;
import com.abx.ainotebook.service.NoteService;
import java.util.Objects;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenInsightController {
    private final InsightService insightService;
    private final NoteService noteService;

    public GenInsightController(InsightService insightService, NoteService noteService) {
        this.insightService = insightService;
        this.noteService = noteService;
    }

    @GetMapping("/note/{noteId}/genInsight")
    public ResponseEntity<String> genInsight(@PathVariable("noteId") UUID noteId) {

        if (Objects.equals(noteId, null)) {
            return ResponseEntity.badRequest().body(null);
        }
        Note note = noteService.findById(noteId);
        String notes = note.getContent();
        var insights = insightService.genInsight(notes);
        return ResponseEntity.ok(insights);
    }

    @GetMapping("/note/{noteId}/genSummary")
    public ResponseEntity<String> genSummary(@PathVariable("noteId") UUID noteId) {
        if (Objects.equals(noteId, null)) {
            return ResponseEntity.badRequest().body(null);
        }
        Note note = noteService.findById(noteId);
        String notes = note.getContent();
        var insights = insightService.genSummary(notes);
        return ResponseEntity.ok(insights);
    }
}
