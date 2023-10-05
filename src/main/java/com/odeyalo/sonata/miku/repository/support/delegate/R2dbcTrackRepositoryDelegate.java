package com.odeyalo.sonata.miku.repository.support.delegate;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * R2DBC specific repository that uses R2dbcRepository to perform operations
 */
@Repository
public interface R2dbcTrackRepositoryDelegate extends R2dbcRepository<TrackEntity, Long> {
    Mono<TrackEntity> findByPublicId(String publicId);

    Flux<TrackEntity> findAllByPublicIdIsIn(String... ids);

    Mono<Void> deleteByPublicId(String id);
}