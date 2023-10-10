package com.odeyalo.sonata.miku.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor(staticName = "pairOf")
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("album_artists")
public class AlbumArtistEntity {
    @Column("artist_id")
    Long artistId;
    @Column("album_id")
    Long albumId;
}