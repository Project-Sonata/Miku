package com.odeyalo.sonata.miku.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeyalo.sonata.miku.model.AlbumType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AlbumDto {
    String id;
    String name;
    @JsonProperty("album_type")
    AlbumType albumType;
    @JsonProperty("release_date")
    LocalDate releaseDate;
    @JsonProperty("total_tracks_count")
    int totalTracksCount;
    @JsonProperty("artists")
    ArtistsDto artists;
    @JsonProperty("tracks")
    SimplifiedTrackDtoContainer tracks;
}