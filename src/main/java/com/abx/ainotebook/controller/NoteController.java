package com.abx.ainotebook.controller;

import com.abx.ainotebook.dto.CreateNoteDto;
import com.abx.ainotebook.dto.NoteDto;
import com.abx.ainotebook.model.Note;
import com.abx.ainotebook.service.NoteBookService;
import com.abx.ainotebook.service.NoteService;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note-api")
public class NoteController {
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final NoteService noteService;
    private final NoteBookService noteBookService;

    public NoteController(NoteService noteService, NoteBookService noteBookService) {
        this.noteService = noteService;
        this.noteBookService = noteBookService;
    }

    @GetMapping("/note/{noteId}")
    public ResponseEntity<Note> getNote(@PathVariable("noteId") UUID noteId) {
        if (Objects.equals(noteId, null)) {
            return ResponseEntity.badRequest().build();
        }
        Note note = noteService.findById(noteId);
        return ResponseEntity.ok(note);
    }

    @GetMapping("/note/list")
    public ResponseEntity<List<Note>> getNotes() {
        List<Note> notes = noteService.findAllNotes();
        return ResponseEntity.ok(notes);
    }

    @DeleteMapping("/note/{noteId}")
    public ResponseEntity<Note> deleteNote(@PathVariable("noteId") UUID noteId) {
        if (Objects.equals(noteId, null)) {
            return ResponseEntity.badRequest().build();
        }
        noteService.delete(noteId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/notebook/{notebookId}")
    public ResponseEntity<List<NoteDto>> getNotesByNotebookId(@PathVariable UUID notebookId) {
        if (Objects.equals(notebookId, null)) {
            return ResponseEntity.badRequest().build();
        }
        List<Note> notes = noteService.findByNotebookId(notebookId);
        List<NoteDto> noteDtos =
                notes.stream().map(noteService::convertNoteToDto).collect(Collectors.toList());
        return ResponseEntity.ok(noteDtos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NoteDto>> getNotesByUserId(@PathVariable UUID userId) {
        if (Objects.equals(userId, null)) {
            return ResponseEntity.badRequest().build();
        }
        List<Note> notes = noteService.findByUserId(userId);
        List<NoteDto> noteDtos =
                notes.stream().map(noteService::convertNoteToDto).collect(Collectors.toList());
        return ResponseEntity.ok(noteDtos);
    }

    @PostMapping("/user/{userId}/notebook/{notebookId}/create")
    public ResponseEntity<NoteDto> createNote(
            @RequestBody CreateNoteDto createNoteDto, @PathVariable UUID userId, @PathVariable UUID notebookId)
            throws Exception {

        if (Objects.equals(createNoteDto.getTitle(), null)) {
            return ResponseEntity.badRequest().build();
        }
        if (Objects.equals(userId, null)) {
            return ResponseEntity.badRequest().build();
        }
        if (Objects.equals(notebookId, null)) {
            return ResponseEntity.badRequest().build();
        }
        Note createdNote = noteService.createNote(createNoteDto, userId, notebookId);
        NoteDto noteDto = noteService.convertNoteToDto(createdNote);
        return ResponseEntity.ok(noteDto);
    }

    @MessageMapping("/note/{noteId}/auto-save")
    public ResponseEntity<String> receiveNote(@DestinationVariable UUID noteId, @RequestBody String noteContent) {
        log("Received Note Content: " + noteContent);
        noteService.modifyNote(noteId, noteContent);
        return ResponseEntity.ok(noteContent);
    }

    private void log(String message) {
        //        System.out.println(message);
        logger.info(message);
    }
}
