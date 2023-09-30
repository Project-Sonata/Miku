package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import reactor.core.publisher.Flux;

/**
 * Repository to work with ArtistEntity
 */
public interface ArtistRepository extends BaseRepository<ArtistEntity, Long> {

    Flux<ArtistEntity> findAllByPublicIdIn(String... ids);

}
