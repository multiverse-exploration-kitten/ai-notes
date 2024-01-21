package com.abx.ainotebook.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Document(collection = "notes")
public class Note {

    @Id
    private UUID id;

    private UUID userId;

    private UUID notebookId;

    public String title;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Note(
        UUID id,
        UUID userId,
        UUID notebookId,
        String title,
        String content,
        Timestamp createdAt,
        Timestamp updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.notebookId = notebookId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Note(UUID id, UUID userId) {
        this.id = id;
        this.userId = userId;
        this.notebookId = null;
        this.title = "untitled";
        this.content = "";
        this.createdAt = Timestamp.from(Instant.now());;
        this.updatedAt = Timestamp.from(Instant.now());;
    }

    public UUID getId() { return this.id; }

    public void setId(UUID id) { this.id = id; }

    public UUID getUserId(UUID userId) {
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

    public void setTitle(String title){
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

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
