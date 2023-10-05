package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.TrackArtistEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository to work with TrackArtistEntity
 */
@Repository
public interface TrackArtistRepository extends BaseRepository<TrackArtistEntity, Long>, TrackArtistRemoveCapable {

    Flux<TrackArtistEntity> findAllByTrackId(Long trackId);

    Flux<TrackArtistEntity> findAllByArtistId(Long artistId);

}