package com.odeyalo.sonata.miku.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Dto to transfer Track info
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackDto {
    String id;
    String name;
    @JsonProperty("duration_ms")
    Long durationMs;
    @JsonProperty("artists")
    ArtistsDto artists;
}