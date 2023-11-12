package com.odeyalo.sonata.miku.support.converter.release;

import com.odeyalo.sonata.miku.model.ReleaseDate;
import org.jetbrains.annotations.NotNull;

/**
 * Metadata about ReleaseDate that presented as string
 *
 * @param date          - date as string
 * @param precisionHint - hint about date precision
 */
public record ReleaseDateInfo(@NotNull String date,
                              @NotNull ReleaseDate.Precision precisionHint) {

    public static ReleaseDateInfo of(String date, ReleaseDate.Precision precisionHint) {
        return new ReleaseDateInfo(date, precisionHint);
    }
}
