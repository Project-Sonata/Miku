package com.odeyalo.sonata.miku.repository.r2dbc.delegate;

import com.odeyalo.sonata.miku.entity.AlbumArtistEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Helper class that used as delegate and invoke the database only using R2DBC
 */
@Repository
public interface R2dbcAlbumArtistRepositoryDelegate extends R2dbcRepository<AlbumArtistEntity, Long> {

    Flux<AlbumArtistEntity> findAllByAlbumId(Long albumId);

    Flux<AlbumArtistEntity> findAllByArtistId(Long artistId);

}
