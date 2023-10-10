package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.write;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.entity.ArtistsContainerHolder;
import com.odeyalo.sonata.miku.repository.ArtistRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * BeforeSaveCallback that save the {@link com.odeyalo.sonata.miku.entity.ArtistsContainerHolder} to
 * {@link ArtistRepository} if the artist is not saved
 */
@Component
public class SaveArtistOnMissingBeforeSaveEntityCallback implements BeforeSaveCallback<ArtistsContainerHolder> {
    private final ArtistRepository artistRepository;

    @Autowired
    public SaveArtistOnMissingBeforeSaveEntityCallback(@Lazy ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    @NotNull
    public Publisher<ArtistsContainerHolder> onBeforeSave(@NotNull ArtistsContainerHolder artistsContainerHolder,
                                                          @NotNull OutboundRow row,
                                                          @NotNull SqlIdentifier table) {

        return Flux.fromIterable(artistsContainerHolder.getArtists())
                .flatMap(this::getOrSaveArtistEntity)
                .collectList()
                .map(savedArtistEntities -> updateArtistsContainerHolder(artistsContainerHolder, savedArtistEntities));
    }

    @NotNull
    private Mono<ArtistEntity> getOrSaveArtistEntity(@NotNull ArtistEntity entity) {
        return artistRepository
                .findByPublicId(entity.getPublicId())
                .switchIfEmpty(artistRepository.save(entity));
    }

    @NotNull
    private ArtistsContainerHolder updateArtistsContainerHolder(@NotNull ArtistsContainerHolder artistContainerParent,
                                                                @NotNull List<ArtistEntity> savedArtistEntities) {

        artistContainerParent.setArtists(savedArtistEntities);

        return artistContainerParent;
    }
}
