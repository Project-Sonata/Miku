package com.odeyalo.sonata.miku.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity to represent the row in table.
 * R2DBC is used now
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("tracks")
public class TrackEntity {
    @Id
    Long id;
    @Column("public_id")
    String publicId;
    String name;
    @Column("duration_ms")
    Long durationMs;
}