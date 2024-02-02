package com.abx.ainotebook.service;

import com.abx.ainotebook.dto.CreateNoteDto;
import com.abx.ainotebook.dto.ImmutableNoteDto;
import com.abx.ainotebook.dto.NoteDto;
import com.abx.ainotebook.model.Note;
import com.abx.ainotebook.repository.NoteRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteDto convertNoteToDto(Note note) {
        if (note == null) {
            return null;
        }
        return ImmutableNoteDto.builder()
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }

    public Note findById(UUID id) {
        return noteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found with ID: " + id));
    }

    public Note createNote(CreateNoteDto createNoteDto, UUID userId, UUID notebookId) throws Exception {
        if (createNoteDto == null || userId == null || notebookId == null) {
            throw new Exception("CreateNoteDto, userId, or notebookId is null");
        }
        Note note = new Note(
                UUID.randomUUID(),
                userId,
                notebookId,
                createNoteDto.getTitle(),
                createNoteDto.getContent(),
                System.currentTimeMillis(),
                System.currentTimeMillis());

        Note savedNote = noteRepository.save(note);
        if (savedNote == null) {
            throw new Exception("Failed to save the note");
        }
        return savedNote;
    }

    public List<Note> findByUserId(UUID userId) {
        return noteRepository.findByUserId(userId);
    }

    public List<Note> findByNotebookId(UUID notebookId) {
        return noteRepository.findByNotebookId(notebookId);
    }

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> findByTitleContaining(String title, UUID userId) {
        return noteRepository.findByTitleContaining(title, userId);
    }

    public void modifyNote(UUID id, String newContent) {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isPresent()) {
            Note note = existingNote.get();
            note.setContent(newContent);
            noteRepository.save(note);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found with ID: " + id);
        }
    }

    public void delete(UUID noteId) {
        if (!noteRepository.existsById(noteId)) {
            throw new ResourceNotFoundException("Note not found with ID: " + noteId);
        }
        noteRepository.deleteById(noteId);
    }
}
