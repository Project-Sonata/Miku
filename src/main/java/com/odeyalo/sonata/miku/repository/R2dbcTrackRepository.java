package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * TrackRepository that uses R2DBC for database interaction
 */
@Repository
public interface R2dbcTrackRepository extends R2dbcRepository<TrackEntity, Long>, TrackRepository {
}