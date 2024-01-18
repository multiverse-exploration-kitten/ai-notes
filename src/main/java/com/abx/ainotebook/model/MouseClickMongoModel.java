package com.abx.ainotebook.model;

import jakarta.persistence.Id;
import java.time.Instant;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "mouseclick")
public class MouseClickMongoModel {
    @Id
    private UUID id;

    @Field
    private UUID userId;

    @Field
    private UUID notebookId;

    @Field("x")
    private int coordX;

    @Field("y")
    private int coordY;

    @Field("target")
    private String targetId;

    private Instant timestamp;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(UUID notebookId) {
        this.notebookId = notebookId;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
