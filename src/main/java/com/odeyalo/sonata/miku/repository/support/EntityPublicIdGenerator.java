package com.odeyalo.sonata.miku.repository.support;

import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

/**
 * Generate public ID for the entity of type T. Generated ID must be unique
 *
 * @param <T> - type of the entity to generate ID to
 */
public interface EntityPublicIdGenerator<T> {

    @NotNull
    Mono<String> generatePublicId(@NotNull T entity);

}
