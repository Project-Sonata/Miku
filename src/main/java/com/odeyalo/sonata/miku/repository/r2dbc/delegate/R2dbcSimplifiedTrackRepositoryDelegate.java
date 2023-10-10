package com.odeyalo.sonata.miku.repository.r2dbc.delegate;

import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface R2dbcSimplifiedTrackRepositoryDelegate extends R2dbcRepository<SimplifiedTrackEntity, Long> {
    Mono<SimplifiedTrackEntity> findByPublicId(String publicId);

    Flux<SimplifiedTrackEntity> findAllByPublicIdIsIn(String... ids);

    Flux<SimplifiedTrackEntity> findAllByAlbumId(Long albumId);

    Mono<Void> deleteByPublicId(String id);
}
