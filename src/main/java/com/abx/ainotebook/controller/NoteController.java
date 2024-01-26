package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.CreateNoteDto;
import com.abx.ainotebook.dto.ImmutableNoteDto;
import com.abx.ainotebook.dto.NoteDto;
import com.abx.ainotebook.model.Note;
import com.abx.ainotebook.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/note/{noteId}")
    public ResponseEntity<Note> getNote(@PathVariable("noteId") UUID noteId) {
        if (Objects.equals(noteId, null)) {
            return ResponseEntity.badRequest().body(null);
        }
        Optional<Note> note = noteService.findById(noteId);
        if (note.isPresent()) {
            return ResponseEntity.of(note);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("user/{userId}/notebook/{notebookId}/create_note")
    public ResponseEntity<NoteDto> createNote(
            @RequestBody CreateNoteDto createNoteDto, @PathVariable UUID userId, @PathVariable UUID notebookId) {
        if (Objects.equals(createNoteDto.getTitle(), null)) {
            return ResponseEntity.badRequest().build();
        }
        Note createdNote = noteService.createNote(createNoteDto, userId, notebookId);
        NoteDto noteDto = ImmutableNoteDto.builder()
                .title(createdNote.getTitle())
                .content(createdNote.getContent())
                .createdAt(createdNote.getCreatedAt())
                .updatedAt(createdNote.getUpdatedAt())
                .build();
        return ResponseEntity.ok(noteDto);
    }
}
