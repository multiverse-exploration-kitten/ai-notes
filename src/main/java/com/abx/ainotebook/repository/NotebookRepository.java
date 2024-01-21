package com.abx.ainotebook.repository;

import com.abx.ainotebook.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface NotebookRepository extends JpaRepository<Notebook, UUID> {
    List<Notebook> findByCategoryUAndUserId(String category, UUID userID);

    List<Notebook> findByTitleAndUserId(String title, UUID userID);

    List<Notebook> findByCreatedAtAfter(Timestamp createdAt);
}
