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
    private UUID notebookId;

    @Field("x")
    private long coordX;

    @Field("y")
    private long coordY;

    @Field("target")
    private String clickedTargetId;

    private Instant timestamp;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(UUID notebookId) {
        this.notebookId = notebookId;
    }

    public long getCoordX() {
        return coordX;
    }

    public void setCoordX(long coordX) {
        this.coordX = coordX;
    }

    public long getCoordY() {
        return coordY;
    }

    public void setCoordY(long coordY) {
        this.coordY = coordY;
    }

    public String getClickedTargetId() {
        return clickedTargetId;
    }

    public void setClickedTargetId(String clickedTargetId) {
        this.clickedTargetId = clickedTargetId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
