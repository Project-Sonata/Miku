package com.odeyalo.sonata.miku.repository.support.callback.r2dbc;

import com.odeyalo.sonata.miku.entity.TrackArtistEntity;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.repository.TrackArtistRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.AfterSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Associate the track and the artist after the TrackEntity has been saved
 */
@Component
public class TrackArtistAssociationAfterSaveEntityCallback implements AfterSaveCallback<TrackEntity> {
    private final TrackArtistRepository trackArtistRepository;

    public TrackArtistAssociationAfterSaveEntityCallback(@Lazy TrackArtistRepository trackArtistRepository) {
        this.trackArtistRepository = trackArtistRepository;
    }

    @Override
    public Publisher<TrackEntity> onAfterSave(TrackEntity track,
                                              @NotNull OutboundRow row,
                                              @NotNull SqlIdentifier table) {

        Flux<TrackArtistEntity> trackArtists = Flux.fromIterable(track.getArtists())
                .map(artist -> TrackArtistEntity.of(track.getId(), artist.getId()));

        return trackArtistRepository.saveAll(trackArtists)
                .then(Mono.just(track));
    }
}
