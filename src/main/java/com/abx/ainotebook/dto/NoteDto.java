package com.abx.ainotebook.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableNoteDto.class)
@JsonDeserialize(as = ImmutableNoteDto.class)
public interface NoteDto {
    String getTitle();

    String getContent();

    long getCreatedAt();

    long getUpdatedAt();

    UUID getId();

    UUID getUserId();

    UUID getNotebookId();
}
