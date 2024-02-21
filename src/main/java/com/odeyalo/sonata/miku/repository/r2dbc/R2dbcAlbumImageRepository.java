package com.odeyalo.sonata.miku.repository.r2dbc;

import com.odeyalo.sonata.miku.entity.AlbumImageEntity;
import com.odeyalo.sonata.miku.repository.AlbumImageRepository;
import com.odeyalo.sonata.miku.repository.r2dbc.delegate.R2dbcAlbumImageRepositoryDelegate;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * R2DBC implementation of {@link AlbumImageRepository}
 */
@Component
public class R2dbcAlbumImageRepository implements AlbumImageRepository {
    private final R2dbcAlbumImageRepositoryDelegate delegate;

    public R2dbcAlbumImageRepository(R2dbcAlbumImageRepositoryDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public @NotNull Flux<AlbumImageEntity> findAllByAlbumId(long albumId) {
        return delegate.findAllByAlbumId(albumId);
    }

    @Override
    public <S extends AlbumImageEntity> Mono<S> save(S entity) {
        return delegate.save(entity);
    }

    @Override
    public <S extends AlbumImageEntity> Flux<S> saveAll(Iterable<S> entities) {
        return delegate.saveAll(entities);
    }

    @Override
    public <S extends AlbumImageEntity> Flux<S> saveAll(Publisher<S> entities) {
        return delegate.saveAll(entities);
    }

    @Override
    public Mono<AlbumImageEntity> findById(Long id) {
        return delegate.findById(id);
    }

    @Override
    public Flux<AlbumImageEntity> findAllById(Iterable<Long> ids) {
        return delegate.findAllById(ids);
    }

    @Override
    public Flux<AlbumImageEntity> findAllById(Publisher<Long> ids) {
        return delegate.findAllById(ids);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return delegate.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAllById(Iterable<Long> ids) {
        return delegate.deleteAllById(ids);
    }

    @Override
    public Mono<Void> deleteAll() {
        return delegate.deleteAll();
    }
}
