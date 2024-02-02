package com.abx.ainotebook.model;

import jakarta.persistence.Id;
<<<<<<< HEAD
=======
import java.time.Instant;
>>>>>>> 7d9e407 (introduced UserEvent type)
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
<<<<<<< HEAD
    private long timestamp;
=======
    private Instant timestamp;
>>>>>>> 7d9e407 (introduced UserEvent type)

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

<<<<<<< HEAD
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
=======
    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
>>>>>>> 7d9e407 (introduced UserEvent type)
        this.timestamp = timestamp;
    }
}
