package com.abx.ainotebook.model;

import java.util.Map;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "embedded_content")
public class EmbeddedModel {

  @Id
  private UUID id;

  private UUID referenceId; // ID of a Note or Notebook

  private String type; // "note" or "notebook" to identify the type of the reference

  private Map<String, Float> embedding;

  private Long createdAt;
  private Integer index;

  public EmbeddedModel(UUID id, UUID referenceId, String type,
                       Map<String, Float> embedding, Long createdAt, Integer index) {
    this.id = id;
    this.referenceId = referenceId;
    this.type = type;
    this.embedding = embedding;
    this.createdAt = createdAt;
    this.index = index;
  }


  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(UUID referenceId) {
    this.referenceId = referenceId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map<String, Float> getEmbedding() {
    return embedding;
  }

  public void setEmbedding(Map<String, Float> embedding) {
    this.embedding = embedding;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }
  public Integer getIndex() { return index; }
  public void setIndex(Integer index) { this.index = index; }
}

