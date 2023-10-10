package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository to work with ArtistEntity
 */
public interface ArtistRepository extends BaseRepository<ArtistEntity, Long> {

    Mono<ArtistEntity> findByPublicId(String publicId);

    Flux<ArtistEntity> findAllByPublicIdIn(String... ids);

}
