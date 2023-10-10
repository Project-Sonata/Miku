package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.read;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.repository.SimplifiedTrackRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

@Component
public class AlbumTrackEnhancerAfterConvertEntityCallback implements AfterConvertCallback<AlbumEntity> {
    private final SimplifiedTrackRepository trackRepository;

    @Autowired
    public AlbumTrackEnhancerAfterConvertEntityCallback(@Lazy SimplifiedTrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    @NotNull
    public Publisher<AlbumEntity> onAfterConvert(@NotNull AlbumEntity entity,
                                                 @NotNull SqlIdentifier table) {

        return trackRepository.findAllByAlbumId(entity.getId())
                .collectList()
                .doOnNext(entity::setTracks)
                .thenReturn(entity);
    }
}
