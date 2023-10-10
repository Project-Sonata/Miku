package com.odeyalo.sonata.miku.entity;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.Assert;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("tracks")
@Accessors(chain = true)
public class SimplifiedTrackEntity implements ArtistsContainerHolder {
    @Id
    Long id;
    @Column("public_id")
    String publicId;
    String name;
    @Column("duration_ms")
    Long durationMs;
    @Column("album_id")
    Long albumId;
    @Singular
    @Transient
    @Setter(value = AccessLevel.NONE)
    List<ArtistEntity> artists;

    @Override
    public void setArtists(List<ArtistEntity> artists) {
        Assert.notNull(artists, "The artists must be not null");
        this.artists = artists;
    }
}
