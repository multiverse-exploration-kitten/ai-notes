package com.abx.ainotebook.service;

import com.abx.ainotebook.dto.CreateNoteDto;
import com.abx.ainotebook.model.Note;
import com.abx.ainotebook.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Optional<Note> findById(UUID id) {
        return noteRepository.findById(id);
    }

    public Note createNote(CreateNoteDto createNoteDto, UUID userId, UUID notebookId) {
        Note note = new Note(userId, notebookId, createNoteDto.getTitle());
        noteRepository.save(note);
        return note;
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

    public void modifyNote(UUID id, String content) {

    }
}
