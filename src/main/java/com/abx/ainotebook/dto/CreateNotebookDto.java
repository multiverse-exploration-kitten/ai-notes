package com.abx.ainotebook.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateNotebookDto.class)
@JsonDeserialize(as = ImmutableCreateNotebookDto.class)
public interface CreateNotebookDto {
    String getTitle();

    String getCategory();
}
