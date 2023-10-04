package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SimplifiedAlbumRepository extends R2dbcRepository<SimplifiedAlbumEntity, Long> {

    Mono<SimplifiedAlbumEntity> findByPublicId(String albumId);

}
