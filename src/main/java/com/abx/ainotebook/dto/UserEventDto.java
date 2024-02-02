package com.abx.ainotebook.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableUserEventDto.class)
@JsonDeserialize(as = ImmutableUserEventDto.class)
public interface UserEventDto {
    UUID getUserId();

    UUID getNoteId();

    String getEventType();

    Map<String, Object> getEventAttributes();
}
