package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.read;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.repository.SimplifiedAlbumRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

@Component
public class TrackAlbumEnhancerAfterConvertEntityCallback implements AfterConvertCallback<TrackEntity> {
    private final SimplifiedAlbumRepository simplifiedAlbumRepository;

    @Autowired
    public TrackAlbumEnhancerAfterConvertEntityCallback(@Lazy SimplifiedAlbumRepository simplifiedAlbumRepository) {
        this.simplifiedAlbumRepository = simplifiedAlbumRepository;
    }

    @Override
    @NotNull
    public Publisher<TrackEntity> onAfterConvert(@NotNull TrackEntity entity,
                                                 @NotNull SqlIdentifier table) {

        return simplifiedAlbumRepository.findById(entity.getAlbumId())
                .doOnNext(entity::setAlbum)
                .thenReturn(entity);
    }
}
