package com.odeyalo.sonata.miku.repository.r2dbc.delegate;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Helper interface that used as delegate to work with AlbumEntity using R2DBC
 */
@Repository
public interface R2dbcAlbumRepositoryDelegate extends R2dbcRepository<AlbumEntity, Long> {

    Mono<AlbumEntity> findByPublicId(String albumId);
}
