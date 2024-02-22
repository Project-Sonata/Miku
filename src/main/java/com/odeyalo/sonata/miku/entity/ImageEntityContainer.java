package com.odeyalo.sonata.miku.entity;

import com.odeyalo.sonata.miku.support.utils.CollectionUtils;
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
    @Getter(value = AccessLevel.PUBLIC)
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

    @Override
    public boolean equals(Object other) {
        if ( this == other ) return true;
        if ( !(other instanceof ImageEntityContainer container) ) return false;

        return CollectionUtils.areEqualDespiteOrder(container.getImages(), this.getImages());
    }

    @Override
    public int hashCode() {
        return getImages().hashCode();
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
