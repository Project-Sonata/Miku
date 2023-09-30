package com.odeyalo.sonata.miku.repository;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Base repository with generic methods
 *
 * @param <T>  - entity type
 * @param <ID> - id of the entity
 */
public interface BaseRepository<T, ID> {

    <S extends T> Mono<S> save(S entity);

    <S extends T> Flux<S> saveAll(Iterable<S> entities);

    <S extends T> Flux<S> saveAll(Publisher<S> entities);

    Mono<T> findById(ID id);

    Flux<T> findAllById(Iterable<ID> ids);

    Flux<T> findAllById(Publisher<ID> ids);

    Mono<Void> deleteById(Long id);

    Mono<Void> deleteAll();

}
