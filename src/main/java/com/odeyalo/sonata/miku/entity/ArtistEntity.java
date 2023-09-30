package com.odeyalo.sonata.miku.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity that contain artist info
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("artists")
public class ArtistEntity {
    @Id
    Long id;
    @Column("public_id")
    String publicId;
    @Column("artist_name")
    String name;
}
