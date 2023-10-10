package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Basic repository to work with tracks that does not depend on any framework or library.
 */
public interface SimplifiedTrackRepository extends BaseRepository<SimplifiedTrackEntity, Long> {

    Mono<SimplifiedTrackEntity> findByPublicId(String publicId);

    Flux<SimplifiedTrackEntity> findAllByPublicIdIsIn(String... ids);

    Flux<SimplifiedTrackEntity> findAllByAlbumId(Long albumId);

    Mono<Void> deleteByPublicId(String id);

}