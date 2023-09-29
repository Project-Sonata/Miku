package com.odeyalo.sonata.miku.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Domain model that represent track info
 */
@Value
@AllArgsConstructor(staticName = "of")
@Builder
public class Track {
    @NotNull @NonNull
    String id;
    @NotNull @NonNull
    String name;
    @NotNull @NonNull
    Long durationMs;
}