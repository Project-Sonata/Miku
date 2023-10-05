package com.odeyalo.sonata.miku.repository;

import reactor.core.publisher.Mono;

/**
 * Specific interface for remove operations only
 *
 * @param <T> the type of the entity this interface can delete
 */
public interface RemoveCapable<T> {
    /**
     * Delete the entity by its ID
     *
     * @param id - if of the entity
     * @return - empty mono
     */
    Mono<Void> deleteById(Long id);

    Mono<Void> deleteAllById(Iterable<Long> ids);

    Mono<Void> deleteAll();
}
