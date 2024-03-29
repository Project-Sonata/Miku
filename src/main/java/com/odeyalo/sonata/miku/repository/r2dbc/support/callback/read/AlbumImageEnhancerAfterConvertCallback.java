package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.read;

import com.odeyalo.sonata.miku.entity.ImageEntityContainer;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.repository.AlbumImageRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

/**
 * Populate the converted {@link SimplifiedAlbumEntity} with cover images
 */
@Component
public final class AlbumImageEnhancerAfterConvertCallback implements AfterConvertCallback<SimplifiedAlbumEntity> {
    private final AlbumImageRepository albumImageRepository;

    public AlbumImageEnhancerAfterConvertCallback(@Lazy AlbumImageRepository albumImageRepository) {
        this.albumImageRepository = albumImageRepository;
    }

    @Override
    @NotNull
    public Publisher<SimplifiedAlbumEntity> onAfterConvert(@NotNull SimplifiedAlbumEntity albumEntity,
                                                           @NotNull SqlIdentifier table) {

        return albumImageRepository.findAllByAlbumId(albumEntity.getId())
                .collectList()
                .doOnNext(albumImageEntities -> albumEntity.setImages(ImageEntityContainer.fromCollection(albumImageEntities)))
                .thenReturn(albumEntity);
    }
}
