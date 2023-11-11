package com.odeyalo.sonata.miku.service.event;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.service.AlbumSaver;
import com.odeyalo.sonata.miku.support.converter.external.AlbumEntitySuiteConverter;
import com.odeyalo.sonata.suite.brokers.events.SonataEvent;
import com.odeyalo.sonata.suite.brokers.events.album.AlbumUploadingFullyFinishedEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Listen to AlbumUploadingFullyFinishedEvent and save uploaded album using AlbumSaver
 */
@Component
public class AlbumUploadingFullyFinishedEventListener implements EventListener {
    private final AlbumEntitySuiteConverter converter;
    private final AlbumSaver albumSaver;

    public AlbumUploadingFullyFinishedEventListener(AlbumEntitySuiteConverter converter,
                                                    AlbumSaver albumSaver) {
        this.converter = converter;
        this.albumSaver = albumSaver;
    }

    @Override
    public Mono<Void> handleEvent(SonataEvent sonataEvent) {
        AlbumEntity albumEntity = convertToAlbumEntity(sonataEvent);

        return albumSaver.saveAlbum(albumEntity).then();
    }

    private AlbumEntity convertToAlbumEntity(SonataEvent event) {

        var albumInfo = ((AlbumUploadingFullyFinishedEvent) event).getBody().getAlbumInfo();

        return converter.toAlbumEntity(albumInfo);
    }

    @Override
    public boolean supports(SonataEvent event) {
        return event instanceof AlbumUploadingFullyFinishedEvent;
    }
}
