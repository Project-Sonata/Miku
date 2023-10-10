package com.odeyalo.sonata.miku.repository.r2dbc;

import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import com.odeyalo.sonata.miku.repository.SimplifiedTrackRepository;
import com.odeyalo.sonata.miku.repository.r2dbc.delegate.R2dbcSimplifiedTrackRepositoryDelegate;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class R2dbcSimplifiedTrackRepository implements SimplifiedTrackRepository {
    private final R2dbcSimplifiedTrackRepositoryDelegate r2dbcSimplifiedTrackRepositoryDelegate;

    public R2dbcSimplifiedTrackRepository(R2dbcSimplifiedTrackRepositoryDelegate r2dbcSimplifiedTrackRepositoryDelegate) {
        this.r2dbcSimplifiedTrackRepositoryDelegate = r2dbcSimplifiedTrackRepositoryDelegate;
    }

    @Override
    public <S extends SimplifiedTrackEntity> Mono<S> save(S entity) {
        return r2dbcSimplifiedTrackRepositoryDelegate.save(entity);
    }

    @Override
    public <S extends SimplifiedTrackEntity> Flux<S> saveAll(Iterable<S> entities) {
        return r2dbcSimplifiedTrackRepositoryDelegate.saveAll(entities);
    }

    @Override
    public <S extends SimplifiedTrackEntity> Flux<S> saveAll(Publisher<S> entities) {
        return r2dbcSimplifiedTrackRepositoryDelegate.saveAll(entities);
    }

    @Override
    public Mono<SimplifiedTrackEntity> findById(Long id) {
        return r2dbcSimplifiedTrackRepositoryDelegate.findById(id);
    }

    @Override
    public Flux<SimplifiedTrackEntity> findAllById(Iterable<Long> ids) {
        return r2dbcSimplifiedTrackRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Flux<SimplifiedTrackEntity> findAllById(Publisher<Long> ids) {
        return r2dbcSimplifiedTrackRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return r2dbcSimplifiedTrackRepositoryDelegate.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAllById(Iterable<Long> ids) {
        return r2dbcSimplifiedTrackRepositoryDelegate.deleteAllById(ids);
    }

    @Override
    public Mono<Void> deleteAll() {
        return r2dbcSimplifiedTrackRepositoryDelegate.deleteAll();
    }

    @Override
    public Mono<SimplifiedTrackEntity> findByPublicId(String publicId) {
        return r2dbcSimplifiedTrackRepositoryDelegate.findByPublicId(publicId);
    }

    @Override
    public Flux<SimplifiedTrackEntity> findAllByPublicIdIsIn(String... ids) {
        return r2dbcSimplifiedTrackRepositoryDelegate.findAllByPublicIdIsIn(ids);
    }

    @Override
    public Flux<SimplifiedTrackEntity> findAllByAlbumId(Long albumId) {
        return r2dbcSimplifiedTrackRepositoryDelegate.findAllByAlbumId(albumId);
    }

    @Override
    public Mono<Void> deleteByPublicId(String id) {
        return r2dbcSimplifiedTrackRepositoryDelegate.deleteByPublicId(id);
    }
}
