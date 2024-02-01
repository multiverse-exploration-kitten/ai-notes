package com.abx.ainotebook.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableKeystroke.class)
@JsonDeserialize(as = ImmutableKeystroke.class)
public interface Keystroke {
    String getPressedKey();

    String getKeystrokeType();
}
