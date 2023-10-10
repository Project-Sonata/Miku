package com.odeyalo.sonata.miku.entity;

import com.odeyalo.sonata.miku.model.AlbumType;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("albums")
@Accessors(chain = true)
public class SimplifiedAlbumEntity implements ArtistsContainerHolder {
    @Id
    Long id;
    @Column("public_id")
    String publicId;
    @Column("album_name")
    String name;
    @Column("album_type")
    AlbumType albumType;
    @Column("total_tracks_count")
    int totalTracksCount;
    @Singular
    @Transient
    @Setter(value = AccessLevel.NONE)
    List<ArtistEntity> artists = new ArrayList<>();

    @Override
    public void setArtists(List<ArtistEntity> artists) {
        Assert.notNull(artists, "Artists must be not null!");
        this.artists = artists;
    }
}