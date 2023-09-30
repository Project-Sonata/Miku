package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Basic repository to work with tracks that does not depend on any framework or library.
 */
public interface TrackRepository extends BaseRepository<TrackEntity, Long> {

    Mono<TrackEntity> findByPublicId(String publicId);

    Flux<TrackEntity> findAllByPublicIdIsIn(String... ids);

    Mono<Void> deleteByPublicId(String id);

}