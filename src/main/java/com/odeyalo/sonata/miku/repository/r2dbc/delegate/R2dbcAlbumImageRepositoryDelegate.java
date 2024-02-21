package com.odeyalo.sonata.miku.repository.r2dbc.delegate;

import com.odeyalo.sonata.miku.entity.AlbumImageEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Used as delegate by {@link com.odeyalo.sonata.miku.repository.r2dbc.R2dbcAlbumImageRepository}
 */
@Repository
public interface R2dbcAlbumImageRepositoryDelegate extends R2dbcRepository<AlbumImageEntity, Long> {

    @NotNull
    Flux<AlbumImageEntity> findAllByAlbumId(long albumId);
}
