package com.abx.ainotebook.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateNoteDto.class)
@JsonDeserialize(as = ImmutableCreateNoteDto.class)
public interface CreateNoteDto {
    String getTitle();

    String getContent();
}
