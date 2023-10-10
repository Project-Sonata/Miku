package com.odeyalo.sonata.miku.repository.r2dbc.delegate;

import com.odeyalo.sonata.miku.entity.TrackArtistEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * R2DBC specific repository to work with TrackArtistEntity
 */
@Repository
public interface R2dbcTrackArtistRepositoryDelegate extends R2dbcRepository<TrackArtistEntity, Long> {

    Mono<Void> deleteAllByTrackId(Long trackId);

    Mono<Void> deleteAllByArtistId(Long artistId);

    Flux<TrackArtistEntity> findAllByTrackId(Long trackId);

    Flux<TrackArtistEntity> findAllByArtistId(Long artistId);
}
