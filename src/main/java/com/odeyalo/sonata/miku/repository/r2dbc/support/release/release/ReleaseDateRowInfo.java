package com.odeyalo.sonata.miku.repository.r2dbc.support.release.release;

import com.odeyalo.sonata.miku.model.ReleaseDate;
import org.jetbrains.annotations.NotNull;

/**
 * Metadata about ReleaseDate that stored in persistent storage
 *
 * @param date          - date as string
 * @param precisionHint - hint about date precision
 */
public record ReleaseDateRowInfo(@NotNull String date,
                                 @NotNull ReleaseDate.Precision precisionHint) {

    public static ReleaseDateRowInfo of(String date, ReleaseDate.Precision precisionHint) {
        return new ReleaseDateRowInfo(date, precisionHint);
    }
}
