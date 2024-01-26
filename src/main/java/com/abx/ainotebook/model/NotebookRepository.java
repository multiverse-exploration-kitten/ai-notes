package com.abx.ainotebook.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, UUID> {
    List<Notebook> findByCategoryUAndUserId(String category, UUID userID);

    List<Notebook> findByTitleAndUserId(String title, UUID userID);

    List<Notebook> findByCreatedAtAfter(Timestamp createdAt);
}
