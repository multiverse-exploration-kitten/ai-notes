package com.abx.ainotebook.model;

import jakarta.persistence.Id;
import java.time.Instant;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "keystroke")
public class KeystrokeMongoModel {
    @Id
    private UUID id;

    @Field
    private UUID notebookId;

    @Field
    private String pressedKey;

    @Field
    private String keystrokeType;

    @Field
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

    public String getPressedKey() {
        return pressedKey;
    }

    public void setPressedKey(String pressedKey) {
        this.pressedKey = pressedKey;
    }

    public String getKeystrokeType() {
        return keystrokeType;
    }

    public void setKeystrokeType(String keystrokeType) {
        this.keystrokeType = keystrokeType;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
