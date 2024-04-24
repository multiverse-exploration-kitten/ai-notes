package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.Notebook;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, UUID> {
    List<Notebook> findByUserId(UUID userId);

    List<Notebook> findByCategoryAndUserId(String category, UUID userID);

    List<Notebook> findByTitleAndUserId(String title, UUID userID);

    List<Notebook> findByCreatedAtAfter(long createdAt);
}
