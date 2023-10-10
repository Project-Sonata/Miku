package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.read;

import com.odeyalo.sonata.miku.entity.AlbumArtistEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.repository.AlbumArtistRepository;
import com.odeyalo.sonata.miku.repository.ArtistRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

/**
 * Retrieve the artists from the ArtistRepository and set it to the SimplifiedAlbumEntity
 */
@Component
public class AlbumArtistEnhancerAfterConvertCallback implements AfterConvertCallback<SimplifiedAlbumEntity> {
    private final AlbumArtistRepository albumArtistRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public AlbumArtistEnhancerAfterConvertCallback(@Lazy AlbumArtistRepository albumArtistRepository,
                                                   @Lazy ArtistRepository artistRepository) {
        this.albumArtistRepository = albumArtistRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    @NotNull
    public Publisher<SimplifiedAlbumEntity> onAfterConvert(@NotNull SimplifiedAlbumEntity entity,
                                                           @NotNull SqlIdentifier table) {

        return albumArtistRepository.findAllByAlbumId(entity.getId())
                .map(AlbumArtistEntity::getArtistId)
                .collectList()
                .flatMap(ids -> artistRepository.findAllById(ids).collectList())
                .doOnNext(entity::setArtists)
                .thenReturn(entity);
    }
}