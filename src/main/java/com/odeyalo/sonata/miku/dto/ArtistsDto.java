package com.odeyalo.sonata.miku.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Container for multiple artists
 */
@Data
@AllArgsConstructor(staticName = "multiple")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArtistsDto implements Iterable<ArtistDto> {
    @JsonProperty("items")
    @Getter(value = AccessLevel.NONE)
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

    public boolean isEmpty() {
        return artists.isEmpty();
    }

    public ArtistDto get(int index) {
        return artists.get(index);
    }

    @NotNull
    @Override
    public Iterator<ArtistDto> iterator() {
        return artists.iterator();
    }
}
