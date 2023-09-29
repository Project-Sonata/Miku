package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Basic repository to work with tracks that does not depend on any framework or library.
 */
public interface TrackRepository {

    <S extends TrackEntity> Mono<S> save(S entity);

    Mono<TrackEntity> findById(Long id);

    Mono<TrackEntity> findByPublicId(String publicId);

    Flux<TrackEntity> findAllByPublicIdIsIn(String... ids);

    Mono<Void> deleteById(Long id);

    Mono<Void> deleteByPublicId(String id);

    Mono<Void> deleteAll();
}