package com.odeyalo.sonata.miku.repository.r2dbc;

import com.odeyalo.sonata.miku.entity.AlbumArtistEntity;
import com.odeyalo.sonata.miku.repository.AlbumArtistRepository;
import com.odeyalo.sonata.miku.repository.r2dbc.delegate.R2dbcAlbumArtistRepositoryDelegate;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * AlbumArtistRepository that uses R2DBC
 */
@Component
public class R2dbcAlbumArtistRepository implements AlbumArtistRepository {
    private final R2dbcAlbumArtistRepositoryDelegate r2dbcAlbumArtistRepositoryDelegate;

    @Autowired
    public R2dbcAlbumArtistRepository(R2dbcAlbumArtistRepositoryDelegate r2dbcAlbumArtistRepositoryDelegate) {
        this.r2dbcAlbumArtistRepositoryDelegate = r2dbcAlbumArtistRepositoryDelegate;
    }

    @Override
    public Flux<AlbumArtistEntity> findAllByAlbumId(Long albumId) {
        return r2dbcAlbumArtistRepositoryDelegate.findAllByAlbumId(albumId);
    }

    @Override
    public Flux<AlbumArtistEntity> findAllByArtistId(Long artistId) {
        return r2dbcAlbumArtistRepositoryDelegate.findAllByArtistId(artistId);
    }

    @Override
    public <S extends AlbumArtistEntity> Mono<S> save(S entity) {
        return r2dbcAlbumArtistRepositoryDelegate.save(entity);
    }

    @Override
    public <S extends AlbumArtistEntity> Flux<S> saveAll(Iterable<S> entities) {
        return r2dbcAlbumArtistRepositoryDelegate.saveAll(entities);
    }

    @Override
    public <S extends AlbumArtistEntity> Flux<S> saveAll(Publisher<S> entities) {
        return r2dbcAlbumArtistRepositoryDelegate.saveAll(entities);
    }

    @Override
    public Mono<AlbumArtistEntity> findById(Long id) {
        return r2dbcAlbumArtistRepositoryDelegate.findById(id);
    }

    @Override
    public Flux<AlbumArtistEntity> findAllById(Iterable<Long> ids) {
        return r2dbcAlbumArtistRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Flux<AlbumArtistEntity> findAllById(Publisher<Long> ids) {
        return r2dbcAlbumArtistRepositoryDelegate.findAllById(ids);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return r2dbcAlbumArtistRepositoryDelegate.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAllById(Iterable<Long> ids) {
        return r2dbcAlbumArtistRepositoryDelegate.deleteAllById(ids);
    }

    @Override
    public Mono<Void> deleteAll() {
        return r2dbcAlbumArtistRepositoryDelegate.deleteAll();
    }
}
