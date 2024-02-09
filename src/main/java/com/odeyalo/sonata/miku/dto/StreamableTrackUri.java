package com.odeyalo.sonata.miku.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

/**
 * Represent single uri that can stream track
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class StreamableTrackUri {
    URI uri;
    String format;

    public static StreamableTrackUri of(String uriStr, String format) {
        return of(URI.create(uriStr), format);
    }

    public static final class Formats {
        public static final String MP3_FILE = "MP3";
    }
}
