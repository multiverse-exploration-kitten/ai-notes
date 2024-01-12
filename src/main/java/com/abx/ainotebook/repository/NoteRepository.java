package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.Note;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByUserId(String userId);

    Optional<Note> findById(String id);

    List<Note> findByTitleContaining(String title);
}
