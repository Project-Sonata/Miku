package com.odeyalo.sonata.miku.entity;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("album_images")
public class AlbumImageEntity {
    @NotNull
    @Column("url")
    String url;
    @Nullable
    @Column("width")
    Integer width;
    @Nullable
    @Column("height")
    Integer height;
    @Column("album_id")
    long albumId;
}
