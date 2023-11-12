package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.read;

import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.model.ReleaseDate;
import com.odeyalo.sonata.miku.model.ReleaseDate.Precision;
import com.odeyalo.sonata.miku.support.converter.release.ReleaseDateDecoder;
import com.odeyalo.sonata.miku.support.converter.release.ReleaseDateInfo;
import org.apache.commons.lang3.EnumUtils;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Callback that invoked after the SimplifiedAlbumEntity converted and used to set ReleaseDate
 */
@Component
public class AlbumReleaseDateEnhancerAfterConvertCallback implements AfterConvertCallback<SimplifiedAlbumEntity> {
    private final ReleaseDateDecoder<ReleaseDateInfo> releaseDateDecoder;

    public AlbumReleaseDateEnhancerAfterConvertCallback(ReleaseDateDecoder<ReleaseDateInfo> releaseDateDecoder) {
        this.releaseDateDecoder = releaseDateDecoder;
    }

    @Override
    @NotNull
    public Publisher<SimplifiedAlbumEntity> onAfterConvert(@NotNull SimplifiedAlbumEntity entity,
                                                           @NotNull SqlIdentifier table) {

        String releaseDate = entity.getReleaseDateAsString();
        Precision precision = toPrecision(entity);

        ReleaseDate date = releaseDateDecoder.decodeReleaseDate(ReleaseDateInfo.of(releaseDate, precision));

        entity.setReleaseDate(date);

        return Mono.just(entity);
    }

    @NotNull
    private static Precision toPrecision(@NotNull SimplifiedAlbumEntity entity) {
        return EnumUtils.getEnum(Precision.class, entity.getReleaseDatePrecision());
    }
}
