package com.abx.ainotebook.service;

import com.abx.ainotebook.dto.CreateNoteDto;
import com.abx.ainotebook.model.Note;
import com.abx.ainotebook.repository.NoteRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.*;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note findById(UUID id) {
        return noteRepository
                .findById(String.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found with ID: " + id));
    }

    public Note createNote(CreateNoteDto createNoteDto, UUID userId, UUID notebookId) {
        Note note = new Note(userId, notebookId, createNoteDto.getTitle());
        return noteRepository.save(note);
    }

    public List<Note> findByUserId(UUID userId) {
        return noteRepository.findByUserId(userId);
    }

    public List<Note> findByNotebookId(UUID notebookId) {
        return noteRepository.findByNotebookId(notebookId);
    }

    public List<Note> findByTitleContaining(String title, UUID userId) {
        return noteRepository.findByTitleContaining(title, userId);
    }

    public void modifyNote(UUID id, String newContent) {
        Optional<Note> existingNote = noteRepository.findById(id.toString());
        if (existingNote.isPresent()) {
            Note note = existingNote.get();
            note.setContent(newContent);
            noteRepository.save(note);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found with ID: " + id);
        }
    }
}
