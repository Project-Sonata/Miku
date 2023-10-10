package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.AlbumArtistEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository to work with AlbumArtistEntity
 */
@Repository
public interface AlbumArtistRepository extends BaseRepository<AlbumArtistEntity, Long> {

    Flux<AlbumArtistEntity> findAllByAlbumId(Long albumId);

    Flux<AlbumArtistEntity> findAllByArtistId(Long artistId);

}
