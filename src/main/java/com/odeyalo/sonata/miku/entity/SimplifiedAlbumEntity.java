package com.odeyalo.sonata.miku.entity;

import com.odeyalo.sonata.miku.model.AlbumType;
import com.odeyalo.sonata.miku.model.ReleaseDate;
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
    @Transient
    ReleaseDate releaseDate;
    @Singular
    @Transient
    @Setter(value = AccessLevel.NONE)
    List<ArtistEntity> artists = new ArrayList<>();
    @Transient
    @Builder.Default
    ImageEntityContainer imageEntities = ImageEntityContainer.empty();
    // A columns to represent the release date
    // written here because of Spring R2DBC does not support embedded values.
    // It can't be achieved using AfterConvertCallback invocation due to lack of Row data, e.g. the values from database cannot be accessed in callback
    // also, can't do this using Spring Converter<ReleaseDate, OutboundRow>
    @Column("release_date")
    @EqualsAndHashCode.Exclude
    String releaseDateAsString;
    @Column("release_date_precision")
    @EqualsAndHashCode.Exclude
    String releaseDatePrecision;

    @Override
    public void setArtists(List<ArtistEntity> artists) {
        Assert.notNull(artists, "Artists must be not null!");
        this.artists = artists;
    }

    public void setImages(ImageEntityContainer imageEntities) {
        Assert.notNull(imageEntities, "Images must be not null!");
        this.imageEntities = imageEntities;
    }
}