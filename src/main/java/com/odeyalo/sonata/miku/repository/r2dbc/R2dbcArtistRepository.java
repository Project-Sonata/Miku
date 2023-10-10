package com.odeyalo.sonata.miku.repository.r2dbc;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.repository.ArtistRepository;
import com.odeyalo.sonata.miku.repository.r2dbc.delegate.R2dbcArtistRepositoryDelegate;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Implementation of ArtistRepository that uses R2DBC as persistence layer
 */
@Component
public class R2dbcArtistRepository implements ArtistRepository {
    private final R2dbcArtistRepositoryDelegate r2dbcArtistRepositoryDelegate;

    public R2dbcArtistRepository(R2dbcArtistRepositoryDelegate r2dbcArtistRepositoryDelegate) {
        this.r2dbcArtistRepositoryDelegate = r2dbcArtistRepositoryDelegate;
    }

    @Override
    public Mono<ArtistEntity> findByPublicId(String publicId) {
        return r2dbcArtistRepositoryDelegate.findByPublicId(publicId);
    }

    @Override
    public Flux<ArtistEntity> findAllByPublicIdIn(String... ids) {
        return r2dbcArtistRepositoryDelegate.findAllByPublicIdIn(List.of(ids));
    }

    @Override
    public <S extends ArtistEntity> Mono<S> save(S entity) {
        return r2dbcArtistRepositoryDelegate.save(entity);
    }

    @Override
    public <S extends ArtistEntity> Flux<S> saveAll(Iterable<S> entities) {
        return r2dbcArtistRepositoryDelegate.saveAll(entities);
    }

    @Override
    public <S extends ArtistEntity> Flux<S> saveAll(Publisher<S> entities) {
        return r2dbcArtistRepositoryDelegate.saveAll(entities);
    }

    @Override
    public Mono<ArtistEntity> findById(Long id) {
        return r2dbcArtistRepositoryDelegate.findById(id);
    }

    @Override
    public Flux<ArtistEntity> findAllById(Iterable<Long> ids) {
        return r2dbcArtistRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Flux<ArtistEntity> findAllById(Publisher<Long> ids) {
        return r2dbcArtistRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return r2dbcArtistRepositoryDelegate.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAllById(Iterable<Long> ids) {
        return r2dbcArtistRepositoryDelegate.deleteAllById(ids);
    }

    @Override
    public Mono<Void> deleteAll() {
        return r2dbcArtistRepositoryDelegate.deleteAll();
    }
}
