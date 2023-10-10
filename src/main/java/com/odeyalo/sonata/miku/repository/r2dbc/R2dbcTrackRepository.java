package com.odeyalo.sonata.miku.repository.r2dbc;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.repository.TrackArtistRemoveCapable;
import com.odeyalo.sonata.miku.repository.TrackRepository;
import com.odeyalo.sonata.miku.repository.r2dbc.delegate.R2dbcTrackRepositoryDelegate;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * TrackRepository that uses R2DBC for database interaction
 */
@Component
public class R2dbcTrackRepository implements TrackRepository {
    private final R2dbcTrackRepositoryDelegate r2dbcTrackRepositoryDelegate;
    private final TrackArtistRemoveCapable trackArtistRemover;

    public R2dbcTrackRepository(R2dbcTrackRepositoryDelegate r2dbcTrackRepositoryDelegate,
                                TrackArtistRemoveCapable trackArtistRemover) {
        this.r2dbcTrackRepositoryDelegate = r2dbcTrackRepositoryDelegate;
        this.trackArtistRemover = trackArtistRemover;
    }

    @Override
    public <S extends TrackEntity> Mono<S> save(S entity) {
        return r2dbcTrackRepositoryDelegate.save(entity);
    }

    @Override
    public <S extends TrackEntity> Flux<S> saveAll(Iterable<S> entities) {
        return r2dbcTrackRepositoryDelegate.saveAll(entities);
    }

    @Override
    public <S extends TrackEntity> Flux<S> saveAll(Publisher<S> entities) {
        return r2dbcTrackRepositoryDelegate.saveAll(entities);
    }

    @Override
    public Mono<TrackEntity> findById(Long id) {
        return r2dbcTrackRepositoryDelegate.findById(id);
    }

    @Override
    public Flux<TrackEntity> findAllById(Iterable<Long> ids) {
        return r2dbcTrackRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Flux<TrackEntity> findAllById(Publisher<Long> ids) {
        return r2dbcTrackRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return findById(id)
                .map(track -> trackArtistRemover.deleteAllByTrackId(track.getId()))
                .then(r2dbcTrackRepositoryDelegate.deleteById(id));
    }

    @Override
    public Mono<Void> deleteAllById(Iterable<Long> ids) {
        return trackArtistRemover.deleteAll()
                .then(r2dbcTrackRepositoryDelegate.deleteAll());
    }

    @Override
    public Mono<Void> deleteAll() {
        return r2dbcTrackRepositoryDelegate.deleteAll();
    }

    @Override
    public Mono<TrackEntity> findByPublicId(String publicId) {
        return r2dbcTrackRepositoryDelegate.findByPublicId(publicId);
    }

    @Override
    public Flux<TrackEntity> findAllByPublicIdIsIn(String... ids) {
        return r2dbcTrackRepositoryDelegate.findAllByPublicIdIsIn(ids);
    }

    @Override
    public Flux<TrackEntity> findAllByAlbumId(Long albumId) {
        return r2dbcTrackRepositoryDelegate.findAllByAlbumId(albumId);
    }

    @Override
    public Mono<Void> deleteByPublicId(String id) {
        return findByPublicId(id)
                .flatMap(track -> trackArtistRemover.deleteAllByTrackId(track.getId()).thenReturn(track))
                .flatMap(track -> r2dbcTrackRepositoryDelegate.deleteById(track.getId()));
    }
}