package com.odeyalo.sonata.miku.repository.r2dbc;

import com.odeyalo.sonata.miku.entity.TrackArtistEntity;
import com.odeyalo.sonata.miku.repository.TrackArtistRepository;
import com.odeyalo.sonata.miku.repository.r2dbc.delegate.R2dbcTrackArtistRepositoryDelegate;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * R2DBC impl of the TrackArtistRepository
 */
@Repository
public class R2dbcTrackArtistRepository implements TrackArtistRepository {
    private final R2dbcTrackArtistRepositoryDelegate r2dbcTrackArtistRepositoryDelegate;

    public R2dbcTrackArtistRepository(R2dbcTrackArtistRepositoryDelegate r2dbcTrackArtistRepositoryDelegate) {
        this.r2dbcTrackArtistRepositoryDelegate = r2dbcTrackArtistRepositoryDelegate;
    }

    @Override
    public <S extends TrackArtistEntity> Mono<S> save(S entity) {
        return r2dbcTrackArtistRepositoryDelegate.save(entity);
    }

    @Override
    public <S extends TrackArtistEntity> Flux<S> saveAll(Iterable<S> entities) {
        return r2dbcTrackArtistRepositoryDelegate.saveAll(entities);
    }

    @Override
    public <S extends TrackArtistEntity> Flux<S> saveAll(Publisher<S> entities) {
        return r2dbcTrackArtistRepositoryDelegate.saveAll(entities);
    }

    @Override
    public Mono<TrackArtistEntity> findById(Long id) {
        return r2dbcTrackArtistRepositoryDelegate.findById(id);
    }

    @Override
    public Flux<TrackArtistEntity> findAllById(Iterable<Long> ids) {
        return r2dbcTrackArtistRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Flux<TrackArtistEntity> findAllById(Publisher<Long> ids) {
        return r2dbcTrackArtistRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return r2dbcTrackArtistRepositoryDelegate.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAllById(Iterable<Long> ids) {
        return r2dbcTrackArtistRepositoryDelegate.deleteAllById(ids);
    }

    @Override
    public Mono<Void> deleteAll() {
        return r2dbcTrackArtistRepositoryDelegate.deleteAll();
    }

    @Override
    public Mono<Void> deleteAllByTrackId(Long trackId) {
        return r2dbcTrackArtistRepositoryDelegate.deleteAllByTrackId(trackId);
    }

    @Override
    public Mono<Void> deleteAllByArtistId(Long artistId) {
        return r2dbcTrackArtistRepositoryDelegate.deleteAllByArtistId(artistId);
    }

    @Override
    public Flux<TrackArtistEntity> findAllByTrackId(Long trackId) {
        return r2dbcTrackArtistRepositoryDelegate.findAllByTrackId(trackId);
    }

    @Override
    public Flux<TrackArtistEntity> findAllByArtistId(Long artistId) {
        return r2dbcTrackArtistRepositoryDelegate.findAllByArtistId(artistId);
    }
}