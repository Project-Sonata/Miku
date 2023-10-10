package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import reactor.core.publisher.Mono;

/**
 * Repository to work with AlbumEntity
 */
public interface AlbumRepository extends BaseRepository<AlbumEntity, Long> {

    Mono<AlbumEntity> findByPublicId(String albumId);
}
