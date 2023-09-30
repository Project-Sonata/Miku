package com.odeyalo.sonata.miku.repository.support.callback.r2dbc;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.repository.ArtistRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * BeforeSaveCallback that save the {@link com.odeyalo.sonata.miku.entity.ArtistEntity} to
 * {@link ArtistRepository} if the ID of the artist is null
 */
@Component
public class SaveArtistOnMissingBeforeSaveEntityCallback implements BeforeSaveCallback<TrackEntity> {

    private final ArtistRepository artistRepository;

    @Autowired
    public SaveArtistOnMissingBeforeSaveEntityCallback(@Lazy ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    @NotNull
    public Publisher<TrackEntity> onBeforeSave(@NotNull TrackEntity track, @NotNull OutboundRow row, @NotNull SqlIdentifier table) {
        return Flux.fromIterable(track.getArtists())
                .filter(artistEntity -> artistEntity.getId() == null)
                .collectList()
                .flatMap(artistEntities -> artistRepository.saveAll(artistEntities).then())
                .thenReturn(track);
    }
}
