package com.abx.ainotebook.service;

import com.abx.ainotebook.model.Notebook;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

public class CreateNotebookDto {
    @Id
    private UUID notebookId;

    private UUID userId;

    private String title;
    private String category;
    private Timestamp createdAt;

    private Timestamp updatedAt;

    public UUID getNotebookId() {
        return notebookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public CreateNotebookDto(
            UUID notebookId, UUID userId, String title, String category, Timestamp createdAt, Timestamp updatedAt) {
        this.notebookId = notebookId;
        this.userId = userId;
        this.title = title;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CreateNotebookDto convertToDto(Notebook notebook) {
        return new CreateNotebookDto(
                notebook.getNotebookId(),
                notebook.getUserId(),
                notebook.getTitle(),
                notebook.getCategory(),
                notebook.getCreatedAt(),
                notebook.getUpdatedAt());
    }
}
