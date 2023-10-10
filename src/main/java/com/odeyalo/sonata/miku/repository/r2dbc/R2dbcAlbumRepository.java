package com.odeyalo.sonata.miku.repository.r2dbc;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.repository.AlbumRepository;
import com.odeyalo.sonata.miku.repository.r2dbc.delegate.R2dbcAlbumRepositoryDelegate;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * AlbumRepository implementation that uses R2DBC.
 */
@Component
public class R2dbcAlbumRepository implements AlbumRepository {
    private final R2dbcAlbumRepositoryDelegate r2dbcAlbumRepositoryDelegate;

    @Autowired
    public R2dbcAlbumRepository(R2dbcAlbumRepositoryDelegate r2dbcAlbumRepositoryDelegate) {
        this.r2dbcAlbumRepositoryDelegate = r2dbcAlbumRepositoryDelegate;
    }

    @Override
    public <S extends AlbumEntity> Mono<S> save(S entity) {
        return r2dbcAlbumRepositoryDelegate.save(entity);
    }

    @Override
    public <S extends AlbumEntity> Flux<S> saveAll(Iterable<S> entities) {
        return r2dbcAlbumRepositoryDelegate.saveAll(entities);
    }

    @Override
    public <S extends AlbumEntity> Flux<S> saveAll(Publisher<S> entities) {
        return r2dbcAlbumRepositoryDelegate.saveAll(entities);
    }

    @Override
    public Mono<AlbumEntity> findById(Long id) {
        return r2dbcAlbumRepositoryDelegate.findById(id);
    }

    @Override
    public Flux<AlbumEntity> findAllById(Iterable<Long> ids) {
        return r2dbcAlbumRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Flux<AlbumEntity> findAllById(Publisher<Long> ids) {
        return r2dbcAlbumRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return r2dbcAlbumRepositoryDelegate.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAllById(Iterable<Long> ids) {
        return r2dbcAlbumRepositoryDelegate.deleteAllById(ids);
    }

    @Override
    public Mono<Void> deleteAll() {
        return r2dbcAlbumRepositoryDelegate.deleteAll();
    }

    @Override
    public Mono<AlbumEntity> findByPublicId(String albumId) {
        return r2dbcAlbumRepositoryDelegate.findByPublicId(albumId);
    }
}
