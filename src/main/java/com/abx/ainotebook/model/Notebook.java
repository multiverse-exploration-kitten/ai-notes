package com.abx.ainotebook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "ai_notes")
public class Notebook {

    @Id
    private UUID notebookId;

    private UUID userId;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Notebook(
            UUID notebookId, UUID userId, String title, String category, Timestamp createdAt, Timestamp updatedAt) {
        this.notebookId = notebookId;
        this.userId = userId;
        this.title = title;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //     TODO: overload constructor
    public Notebook(UUID userId, String title, String category) {
        this.userId = userId;
        this.title = title;
        this.category = category;
    }
    //    public Note()

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

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
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
