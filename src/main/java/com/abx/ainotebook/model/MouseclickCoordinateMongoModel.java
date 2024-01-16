package com.abx.ainotebook.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "mouseclick_coordinate")
public class MouseclickCoordinateMongoModel {
    @Id
    private UUID id;

    @Field
    private UUID user_id;

    @Field
    private UUID notebook_id;

    @Field("x")
    private int coordX;

    @Field("y")
    private int coordY;

    private Instant timestamp;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getNotebook_id() {
        return notebook_id;
    }

    public void setNotebook_id(UUID notebook_id) {
        this.notebook_id = notebook_id;
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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
