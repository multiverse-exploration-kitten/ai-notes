package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.Note;
import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, UUID> {
    List<Note> findByUserId(UUID userId);

    List<Note> findByNotebookId(UUID notebookId);

    List<Note> findByTitleContaining(String title, UUID userId);
}
