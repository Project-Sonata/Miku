package com.odeyalo.sonata.miku.service;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

public interface AlbumSaver {

    /**
     * Save the given entity to persistent storage to access it later
     *
     * @param toSave - entity to save
     * @return - mono with saved entity or mono with error
     */
    @NotNull
    Mono<AlbumEntity> saveAlbum(@NotNull AlbumEntity toSave);

}
