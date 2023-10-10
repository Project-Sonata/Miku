package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.read;

import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import com.odeyalo.sonata.miku.entity.TrackArtistEntity;
import com.odeyalo.sonata.miku.repository.ArtistRepository;
import com.odeyalo.sonata.miku.repository.TrackArtistRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

/**
 * Retrieve the artists from the ArtistRepository and set it to the TrackEntity
 */
@Component
public class TrackArtistEnhancerAfterConvertCallback implements AfterConvertCallback<SimplifiedTrackEntity> {
    private final TrackArtistRepository trackArtistRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public TrackArtistEnhancerAfterConvertCallback(@Lazy TrackArtistRepository trackArtistRepository,
                                                   @Lazy ArtistRepository artistRepository) {
        this.trackArtistRepository = trackArtistRepository;
        this.artistRepository = artistRepository;
    }


    @Override
    @NotNull
    public Publisher<SimplifiedTrackEntity> onAfterConvert(@NotNull SimplifiedTrackEntity entity,
                                                           @NotNull SqlIdentifier table) {

        return trackArtistRepository.findAllByTrackId(entity.getId())
                .map(TrackArtistEntity::getArtistId)
                .collectList()
                .flatMap(ids -> artistRepository.findAllById(ids).collectList())
                .doOnNext(entity::setArtists)
                .thenReturn(entity);
    }
}
