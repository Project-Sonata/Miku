package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.TrackArtistEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * R2DBC impl of the TrackArtistRepository
 */
@Repository
public interface R2dbcTrackArtistRepository
        extends R2dbcRepository<TrackArtistEntity, Long>, TrackArtistRepository {
}