package com.odeyalo.sonata.miku.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Iterator;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimplifiedTrackDtoContainer implements Iterable<SimplifiedTrackDto> {
    @JsonProperty("items")
    @Getter(value = AccessLevel.NONE)
    List<SimplifiedTrackDto> items;

    public static SimplifiedTrackDtoContainer multiple(List<SimplifiedTrackDto> tracks) {
        return of(tracks);
    }

    public int size() {
        return items.size();
    }

    public boolean contains(SimplifiedTrackDto o) {
        return items.contains(o);
    }

    @Override
    public Iterator<SimplifiedTrackDto> iterator() {
        return items.iterator();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public SimplifiedTrackDto get(int index) {
        return items.get(index);
    }
}
