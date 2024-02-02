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
    private final InsightService insightService;

    public NoteService(NoteRepository noteRepository, InsightService insightService) {
        this.noteRepository = noteRepository;
        this.insightService = insightService;
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
            //            update later
            insightService.genInsight(newContent);
            insightService.genSummary(newContent);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found with ID: " + id);
        }
    }

    public void delete(UUID noteId) {
        if (!noteRepository.existsById(noteId.toString())) {
            throw new ResourceNotFoundException("Note not found with ID: " + noteId);
        }
        noteRepository.deleteById(noteId.toString());
    }
}
