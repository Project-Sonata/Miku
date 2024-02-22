package com.odeyalo.sonata.miku.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class ImageDtoContainer implements Iterable<ImageDto> {
    @Singular
    List<ImageDto> items;

    public static ImageDtoContainer empty() {
        return builder().build();
    }

    public static ImageDtoContainer single(ImageDto image) {
        return builder().item(image).build();
    }

    public static ImageDtoContainer fromCollection(Collection<? extends ImageDto> images) {
        return builder().items(images).build();
    }

    public static ImageDtoContainer fromArray(ImageDto... images) {
        return fromCollection(List.of(images));
    }

    public int size() {
        return getItems().size();
    }

    public boolean isEmpty() {
        return getItems().isEmpty();
    }

    public boolean contains(Object o) {
        return getItems().contains(o);
    }

    public ImageDto get(int index) {
        return getItems().get(index);
    }

    @NotNull
    @Override
    public Iterator<ImageDto> iterator() {
        return getItems().iterator();
    }
}
