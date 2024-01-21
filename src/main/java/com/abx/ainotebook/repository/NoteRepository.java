package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    Optional<Note> findById(UUID id);

    List<Note> findByUserId(UUID userId);

    List<Note> findByNotebookId(UUID notebookId);

    List<Note> findByTitleContaining(String title, UUID userId);
}
