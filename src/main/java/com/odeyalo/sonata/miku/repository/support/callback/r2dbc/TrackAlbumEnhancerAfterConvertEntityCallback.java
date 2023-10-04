package com.odeyalo.sonata.miku.repository.support.callback.r2dbc;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.repository.SimplifiedAlbumRepository;
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
    public Publisher<TrackEntity> onAfterConvert(TrackEntity entity, SqlIdentifier table) {
        return simplifiedAlbumRepository.findById(entity.getAlbumId())
                .doOnNext(entity::setAlbum)
                .thenReturn(entity);
    }
}
