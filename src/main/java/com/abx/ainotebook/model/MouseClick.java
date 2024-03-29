package com.abx.ainotebook.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMouseClick.class)
@JsonDeserialize(as = ImmutableMouseClick.class)
public interface MouseClick {
    long getX();

    long getY();

    String getClickedTarget();
}
