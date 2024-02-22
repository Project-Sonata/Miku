package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.AlbumImageEntity;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;

/**
 * Repository to work with {@link AlbumImageEntity}
 */
public interface AlbumImageRepository extends BaseRepository<AlbumImageEntity, Long> {

    @NotNull
    Flux<AlbumImageEntity> findAllByAlbumId(long albumId);
}
