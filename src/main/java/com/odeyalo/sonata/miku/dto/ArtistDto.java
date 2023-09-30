package com.odeyalo.sonata.miku.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Dto with artist info
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtistDto {
    String id;
    String name;
}