package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.write;

import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.model.ReleaseDate;
import com.odeyalo.sonata.miku.repository.r2dbc.support.release.release.ReleaseDateEncoder;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.r2dbc.core.Parameter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Encode the {@link ReleaseDate} to String and save it
 */
@Component
public class ReleaseDateAssociationReleaseAlbumEntityBeforeSaveCallback implements BeforeSaveCallback<SimplifiedAlbumEntity> {
    private final ReleaseDateEncoder<String> stringReleaseDateEncoder;

    @Autowired
    public ReleaseDateAssociationReleaseAlbumEntityBeforeSaveCallback(ReleaseDateEncoder<String> stringReleaseDateEncoder) {
        this.stringReleaseDateEncoder = stringReleaseDateEncoder;
    }

    @Override
    @NotNull
    public Publisher<SimplifiedAlbumEntity> onBeforeSave(
            @NotNull SimplifiedAlbumEntity entity,
            @NotNull OutboundRow row,
            @NotNull SqlIdentifier table) {

        ReleaseDate releaseDate = entity.getReleaseDate();

        String encodedReleaseDate = stringReleaseDateEncoder.encodeReleaseDate(releaseDate);

        @SuppressWarnings("deprecation")
        Parameter releaseDateParameter = Parameter.from(encodedReleaseDate);

        @SuppressWarnings("deprecation")
        Parameter releaseDatePrecisionParameter = Parameter.from(releaseDate.getPrecision().name());


        row.append("release_date", releaseDateParameter);

        row.append("release_date_precision", releaseDatePrecisionParameter);

        return Mono.just(entity);
    }
}
