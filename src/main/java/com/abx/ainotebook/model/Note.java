package com.abx.ainotebook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.UUID;
@Entity
@Table(name = "ai_notes")
public class Note {

    @Id
    private UUID notebookId;
    private UUID userId;
    @Column(name = "title")
    private String title;
    @Column(name="category")
    private String category;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Note(UUID notebookId, UUID userId, String title, String category, Timestamp createdAt){
        this.notebookId = notebookId;
        this.userId = userId;
        this.title = title;
        this.category = category;
        this.createdAt = createdAt;
    }
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

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setNotebookId(UUID notebookId) {
        this.notebookId = notebookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
