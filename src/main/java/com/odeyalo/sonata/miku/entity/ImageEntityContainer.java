package com.odeyalo.sonata.miku.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Container for {@link AlbumImageEntity}, used to simplify usage of album cover images
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageEntityContainer implements Iterable<AlbumImageEntity> {
    @Getter(value = AccessLevel.PRIVATE)
    @Singular
    List<AlbumImageEntity> images = new ArrayList<>();

    public static ImageEntityContainer empty() {
        return builder().build();
    }

    public static ImageEntityContainer single(AlbumImageEntity image) {
        return builder().image(image).build();
    }

    public static ImageEntityContainer fromCollection(Collection<AlbumImageEntity> images) {
        return builder().images(images).build();
    }

    public static ImageEntityContainer fromArray(AlbumImageEntity... images) {
        return fromCollection(List.of(images));
    }

    public int size() {
        return getImages().size();
    }

    public boolean isEmpty() {
        return getImages().isEmpty();
    }

    public AlbumImageEntity get(int index) {
        return getImages().get(index);
    }

    @NotNull
    public Stream<AlbumImageEntity> stream() {
        return getImages().stream();
    }

    @NotNull
    @Override
    public Iterator<AlbumImageEntity> iterator() {
        return images.iterator();
    }
}
