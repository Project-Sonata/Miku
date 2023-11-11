package com.odeyalo.sonata.miku.service;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.repository.AlbumRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RepositoryDelegateAlbumSaver implements AlbumSaver {
    private final AlbumRepository albumRepository;

    @Autowired
    public RepositoryDelegateAlbumSaver(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    @NotNull
    public Mono<AlbumEntity> saveAlbum(@NotNull AlbumEntity toSave) {
        return albumRepository.save(toSave);
    }
}
