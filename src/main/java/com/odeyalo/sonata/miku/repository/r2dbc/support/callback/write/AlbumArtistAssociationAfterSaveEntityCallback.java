package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.write;

import com.odeyalo.sonata.miku.entity.AlbumArtistEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.repository.AlbumArtistRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.AfterSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Flux.fromIterable;

/**
 * Associate the album and the artist after the SimplifiedAlbumEntity has been saved
 */
@Component
public class AlbumArtistAssociationAfterSaveEntityCallback implements AfterSaveCallback<SimplifiedAlbumEntity> {
    private final AlbumArtistRepository albumArtistRepository;

    @Autowired
    public AlbumArtistAssociationAfterSaveEntityCallback(@Lazy AlbumArtistRepository albumArtistRepository) {
        this.albumArtistRepository = albumArtistRepository;
    }

    @Override
    @NotNull
    public Publisher<SimplifiedAlbumEntity> onAfterSave(@NotNull SimplifiedAlbumEntity album,
                                                        @NotNull OutboundRow row,
                                                        @NotNull SqlIdentifier table) {


        Flux<AlbumArtistEntity> trackArtists = fromIterable(album.getArtists())
                .map(artist -> AlbumArtistEntity.pairOf(artist.getId(), album.getId()));

        return albumArtistRepository.saveAll(trackArtists)
                .then(Mono.just(album));
    }
}