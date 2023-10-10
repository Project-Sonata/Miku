package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.write;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.repository.SimplifiedTrackRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.AfterSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

import static reactor.core.publisher.Flux.fromIterable;

@Component
public class SaveTrackOnMissingBeforeSaveEntityCallback implements AfterSaveCallback<AlbumEntity> {
    private final SimplifiedTrackRepository trackRepository;

    @Autowired
    public SaveTrackOnMissingBeforeSaveEntityCallback(@Lazy SimplifiedTrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    @NotNull
    public Publisher<AlbumEntity> onAfterSave(@NotNull AlbumEntity albumEntity,
                                              @NotNull OutboundRow row,
                                              @NotNull SqlIdentifier table) {

        return fromIterable(albumEntity.getTracks())
                .filter(track -> track.getId() == null)
                .doOnNext(track -> track.setAlbumId(albumEntity.getId()))
                .collectList()
                .flatMap(tracks -> trackRepository.saveAll(tracks).then())
                .thenReturn(albumEntity);
    }
}