package com.odeyalo.sonata.miku.entity;

import com.odeyalo.sonata.miku.model.AlbumType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("albums")
public class SimplifiedAlbumEntity {
    @Id
    Long id;
    @Column("public_id")
    String publicId;
    @Column("album_name")
    String name;
    @Column("album_type")
    AlbumType albumType;
}
