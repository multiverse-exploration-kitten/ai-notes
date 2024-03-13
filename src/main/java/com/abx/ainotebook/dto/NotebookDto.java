package com.abx.ainotebook.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableNotebookDto.class)
@JsonDeserialize(as = ImmutableNotebookDto.class)
public interface NotebookDto {
    String getTitle();

    String getCategory();

    long getCreatedAt();

    UUID userID();

    long getUpdatedAt();
}
