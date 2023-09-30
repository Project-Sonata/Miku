package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementation of ArtistRepository that uses R2DBC as persistence layer
 */
@Repository
public interface R2dbcArtistRepository extends R2dbcRepository<ArtistEntity, Long>, ArtistRepository {
}
