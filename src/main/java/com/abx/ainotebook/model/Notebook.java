package com.abx.ainotebook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private long createdAt;

    @Column(name = "updated_at")
    private long updatedAt;

    public Notebook(UUID notebookId, UUID userId, String title, String category, long createdAt, long updatedAt) {
        this.notebookId = notebookId;
        this.userId = userId;
        this.title = title;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Notebook(UUID userId, String title, String category) {
        this.userId = userId;
        this.title = title;
        this.category = category;
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

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
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

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
