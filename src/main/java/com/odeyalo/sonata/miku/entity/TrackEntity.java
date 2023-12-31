package com.odeyalo.sonata.miku.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity to represent the row in table.
 * R2DBC is used now
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("tracks")
public class TrackEntity extends SimplifiedTrackEntity {
    @Transient
    SimplifiedAlbumEntity album;
}