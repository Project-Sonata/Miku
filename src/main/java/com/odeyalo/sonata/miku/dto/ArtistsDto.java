package com.odeyalo.sonata.miku.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Container for multiple artists
 */
@Data
@AllArgsConstructor(staticName = "multiple")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtistsDto {
    @JsonProperty("items")
    List<ArtistDto> artists;

    public static ArtistsDto empty() {
        return new ArtistsDto(Collections.emptyList());
    }

    public static ArtistsDto single(ArtistDto artistDto) {
        Assert.notNull(artistDto, "ArtistDto cannot be null!");
        return new ArtistsDto(List.of(artistDto));
    }

    public int size() {
        return artists.size();
    }
}
