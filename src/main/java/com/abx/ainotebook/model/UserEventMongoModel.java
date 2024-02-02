package com.abx.ainotebook.model;

import jakarta.persistence.Id;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user-event")
public class UserEventMongoModel {
    @Id
    private UUID id;

    @Field
    private UUID userId;

    @Field
    private UUID noteId;

    @Field
    private String eventType;

    @Field
    private Map<String, Object> eventAttributes;

    @Field
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

    public UUID getNoteId() {
        return noteId;
    }

    public void setNoteId(UUID noteId) {
        this.noteId = noteId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Map<String, Object> getEventAttributes() {
        return eventAttributes;
    }

    public void setEventAttributes(Map<String, Object> eventAttributes) {
        this.eventAttributes = eventAttributes;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
