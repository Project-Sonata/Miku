package com.odeyalo.sonata.miku.repository;

import reactor.core.publisher.Mono;

/**
 * Interface for remove operations only for Many-to-Many table called TrackArtist
 * Does not implement {@link RemoveCapable} because track_artists table is conjunction and does not contain any ID
 */
public interface TrackArtistRemoveCapable {

    Mono<Void> deleteAllByTrackId(Long trackId);

    Mono<Void> deleteAllByArtistId(Long artistId);

    Mono<Void> deleteAll();
}
