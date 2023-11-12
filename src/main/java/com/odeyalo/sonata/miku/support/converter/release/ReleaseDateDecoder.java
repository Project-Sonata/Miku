package com.odeyalo.sonata.miku.support.converter.release;

import com.odeyalo.sonata.miku.model.ReleaseDate;
import org.jetbrains.annotations.NotNull;

/**
 * Decode the release date from the given source
 *
 * @param <S> - source
 */
public interface ReleaseDateDecoder<S> {

    @NotNull
    ReleaseDate decodeReleaseDate(@NotNull S source);

}
