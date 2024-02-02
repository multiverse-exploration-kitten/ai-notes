package com.abx.ainotebook.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.sql.Timestamp;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableNotebookDto.class)
@JsonDeserialize(as = ImmutableNotebookDto.class)
public interface NotebookDto {
    String getTitle();

    String getCategory();

    Timestamp getCreatedAt();

    Timestamp getUpdatedAt();

    UUID userID();
}
