package com.odeyalo.sonata.miku.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Container for streamable uris
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StreamableTrackUris implements Iterable<StreamableTrackUri> {
    @Singular
    List<StreamableTrackUri> items;

    public static StreamableTrackUris single(StreamableTrackUri item) {
        return builder().item(item).build();
    }

    public static StreamableTrackUris fromCollection(Collection<? extends StreamableTrackUri> items) {
        return builder().items(items).build();
    }

    public static StreamableTrackUris fromArray(StreamableTrackUri... items) {
        return builder().items(List.of(items)).build();
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

    public StreamableTrackUri get(int index) {
        return getItems().get(index);
    }

    public Stream<StreamableTrackUri> stream() {
        return getItems().stream();
    }

    @NotNull
    @Override
    public Iterator<StreamableTrackUri> iterator() {
        return items.iterator();
    }
}
