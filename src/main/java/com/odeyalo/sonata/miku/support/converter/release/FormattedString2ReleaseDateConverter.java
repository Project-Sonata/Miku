package com.odeyalo.sonata.miku.support.converter.release;

import com.odeyalo.sonata.miku.model.ReleaseDate;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static com.odeyalo.sonata.miku.model.ReleaseDate.*;
import static java.lang.String.format;

/**
 * ReleaseDateConverter that uses formatted string for encoding and decoding the release date
 */
@Component
public class FormattedString2ReleaseDateConverter implements ReleaseDateConverter<ReleaseDateInfo, String> {
    private static final String DATE_SPLITERATOR = "-";

    @Override
    @NotNull
    public ReleaseDate decodeReleaseDate(@NotNull ReleaseDateInfo source) {
        String releaseDate = source.date();
        ReleaseDate.Precision precision = source.precisionHint();

        try {
            return switch (precision) {
                case YEAR -> ReleaseDateParserSupport.parseYearAwareReleaseDate(releaseDate);
                case MONTH -> ReleaseDateParserSupport.parseMonthAwareReleaseDate(releaseDate);
                case DAY -> ReleaseDateParserSupport.parseDayAwareReleaseDate(releaseDate);
            };
        } catch (Exception ex) {
            throw new IllegalArgumentException(format("Precision hint with date mismatch. Expected date to be: %s but was: %s", precision.name(), releaseDate));
        }
    }

    @Override
    @NotNull
    public String encodeReleaseDate(@NotNull ReleaseDate releaseDate) {
        ReleaseDateStringBuilder builder = new ReleaseDateStringBuilder(DATE_SPLITERATOR);

        switch (releaseDate.getPrecision()) {
            case YEAR -> appendForYear(releaseDate, builder);
            case MONTH -> appendForMonth(releaseDate, builder);
            case DAY -> appendForDay(releaseDate, builder);
        }

        return builder.buildReleaseDateAsString();
    }

    @NotNull
    private static ReleaseDateStringBuilder appendForYear(@NotNull ReleaseDate releaseDate, ReleaseDateStringBuilder builder) {
        return builder.appendYear(releaseDate.getYear());
    }

    @NotNull
    private static ReleaseDateStringBuilder appendForMonth(@NotNull ReleaseDate releaseDate, ReleaseDateStringBuilder builder) {
        return appendForYear(releaseDate, builder)
                .appendMonth(releaseDate.getMonth());
    }

    @NotNull
    private static ReleaseDateStringBuilder appendForDay(@NotNull ReleaseDate releaseDate, ReleaseDateStringBuilder builder) {
        return appendForMonth(releaseDate, builder)
                .appendDay(releaseDate.getDay());
    }

    private static class ReleaseDateStringBuilder {

        private final String spliterator;
        private final StringBuilder builder = new StringBuilder();

        private ReleaseDateStringBuilder(String spliterator) {
            this.spliterator = spliterator;
        }

        public ReleaseDateStringBuilder appendDay(Integer day) {
            Assert.notNull(day, "Day cannot be null!");

            builder.append(spliterator);

            appendZeroIfNecessary(day);

            return this;
        }

        public ReleaseDateStringBuilder appendMonth(Integer month) {
            Assert.notNull(month, "Month cannot be null!");

            builder.append(spliterator);

            appendZeroIfNecessary(month);

            return this;
        }

        public ReleaseDateStringBuilder appendYear(Integer year) {
            Assert.notNull(year, "Year cannot be null!");
            builder.append(year);
            return this;
        }

        public String buildReleaseDateAsString() {
            return builder.toString();
        }

        private void appendZeroIfNecessary(Integer num) {
            builder.append(num >= 10 ? num : "0" + num);
        }

    }

    private static class ReleaseDateParserSupport {
        private static final String DAY_AWARE_RELEASE_DATE_FORMAT_PATTERN = "yyyy-MM-dd";
        private static final String MONTH_AWARE_RELEASE_DATE_FORMAT_PATTERN = "yyyy-MM";

        public static ReleaseDate parseYearAwareReleaseDate(String releaseDate) {
            Year year = Year.parse(releaseDate);

            return onlyYear(year.getValue());
        }

        public static ReleaseDate parseMonthAwareReleaseDate(String releaseDate) {
            YearMonth localDate = YearMonth.parse(releaseDate, DateTimeFormatter.ofPattern(MONTH_AWARE_RELEASE_DATE_FORMAT_PATTERN));

            return withMonth(localDate.getMonthValue(), localDate.getYear());
        }

        public static ReleaseDate parseDayAwareReleaseDate(String releaseDate) {
            LocalDate localDate = getLocalDate(DAY_AWARE_RELEASE_DATE_FORMAT_PATTERN, releaseDate);

            return withDay(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        }

        public static LocalDate getLocalDate(String pattern, String releaseDate) {
            return LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern(pattern));
        }
    }
}


