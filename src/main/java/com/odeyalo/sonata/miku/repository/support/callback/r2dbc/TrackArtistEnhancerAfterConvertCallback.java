package com.odeyalo.sonata.miku.repository.support.callback.r2dbc;

import com.odeyalo.sonata.miku.entity.TrackArtistEntity;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.repository.ArtistRepository;
import com.odeyalo.sonata.miku.repository.TrackArtistRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Retrieve the artists from the ArtistRepository and set it to the TrackEntity
 */
@Component
public class TrackArtistEnhancerAfterConvertCallback implements AfterConvertCallback<TrackEntity> {
    private final TrackArtistRepository trackArtistRepository;
    private final ArtistRepository artistRepository;

    public TrackArtistEnhancerAfterConvertCallback(@Lazy TrackArtistRepository trackArtistRepository,
                                                   @Lazy ArtistRepository artistRepository) {
        this.trackArtistRepository = trackArtistRepository;
        this.artistRepository = artistRepository;
    }


    @Override
    @NotNull
    public Publisher<TrackEntity> onAfterConvert(TrackEntity entity, @NotNull SqlIdentifier table) {
        return trackArtistRepository.findAllByTrackId(entity.getId())
                .map(TrackArtistEntity::getArtistId)
                .collectList()
                .flatMap(longs -> artistRepository.findAllById(longs).collectList())
                .defaultIfEmpty(Collections.emptyList())
                .map(artists -> {
                    entity.setArtists(artists);
                    return entity;
                });
    }
}
