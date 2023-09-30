package com.odeyalo.sonata.miku.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Conjunction table for Many-to-Many relationship between artists and tracks
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("tracks_artists")
public class TrackArtistEntity {
    @Column("track_id")
    Long trackId;
    @Column("artist_id")
    Long artistId;
}