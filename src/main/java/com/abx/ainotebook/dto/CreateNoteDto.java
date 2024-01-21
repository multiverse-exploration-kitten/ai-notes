package com.abx.ainotebook.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableNoteDto.class)
@JsonDeserialize(as = ImmutableNoteDto.class)
public interface CreateNoteDto {
    String getTitle();
}
