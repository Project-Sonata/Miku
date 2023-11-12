package com.odeyalo.sonata.miku.repository.r2dbc.support.release.release;

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
