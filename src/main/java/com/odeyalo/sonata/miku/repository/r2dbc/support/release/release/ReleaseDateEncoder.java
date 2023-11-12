package com.odeyalo.sonata.miku.repository.r2dbc.support.release.release;

import com.odeyalo.sonata.miku.model.ReleaseDate;
import org.jetbrains.annotations.NotNull;

/**
 * Encode the given ReleaseDate to the given type
 *
 * @param <T> - type to convert the release date
 */
public interface ReleaseDateEncoder<T> {

    @NotNull
    T encodeReleaseDate(@NotNull ReleaseDate releaseDate);

}
