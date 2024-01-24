package com.abx.ainotebook.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMouseClick.class)
@JsonDeserialize(as = ImmutableMouseClick.class)
public interface MouseClick {
    UUID getNotebookId();

    long getX();

    long getY();

    String getClickedTarget();
}
