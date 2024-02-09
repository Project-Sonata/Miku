package com.odeyalo.sonata.miku.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response with supported streaming uris
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "from")
@Builder
public class TrackStreamingResponseDto {
    StreamableTrackUris uris;
}
