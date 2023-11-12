package com.odeyalo.sonata.miku.model;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@AllArgsConstructor(staticName = "of", access = AccessLevel.PROTECTED)
@Builder
public class ReleaseDate {
    @Nullable
    Integer day;
    @Nullable
    Integer month;
    @NotNull
    @NonNull
    Integer year;
    @NotNull
    @NonNull
    Precision precision;

    public static ReleaseDate withDay(Integer day, Integer month, Integer year) {
        return builder()
                .day(day)
                .month(month)
                .year(year)
                .precision(Precision.DAY)
                .build();
    }

    public static ReleaseDate withMonth(Integer month, Integer year) {
        return builder()
                .month(month)
                .year(year)
                .precision(Precision.MONTH)
                .build();
    }

    public static ReleaseDate onlyYear(Integer year) {
        return builder()
                .year(year)
                .precision(Precision.YEAR)
                .build();
    }

    public enum Precision {
        DAY,
        MONTH,
        YEAR
    }
}