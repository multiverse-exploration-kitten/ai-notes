package com.abx.ainotebook.model;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Note {

    @Id
    private UUID id;

    private UUID userId;

    private UUID notebookId;

    private String title;

    private String content;

    private Long createdAt;

    private Long updatedAt;

    // TODO:
    //  private Map<String, Object> metadata;

    public Note() {}

    public Note(UUID id, UUID userId, UUID notebookId, String title, String content, Long createdAt, Long updatedAt) {
        this.id = id;
        this.userId = userId;
        this.notebookId = notebookId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Note(UUID id, UUID userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getNotebookId() {
        return this.notebookId;
    }

    public void setNotebookId(UUID notebookId) {
        this.notebookId = notebookId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
