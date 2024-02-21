package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.write;

import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.repository.r2dbc.R2dbcAlbumImageRepository;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.AfterSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Saves the {@link com.odeyalo.sonata.miku.entity.AlbumImageEntity} to database after {@link SimplifiedAlbumEntity} has been saved
 */
@Component
public final class AlbumImagesAssociationAfterSaveEntityCallback implements AfterSaveCallback<SimplifiedAlbumEntity> {
    private final R2dbcAlbumImageRepository albumImageRepository;

    public AlbumImagesAssociationAfterSaveEntityCallback(@Lazy R2dbcAlbumImageRepository albumImageRepository) {
        this.albumImageRepository = albumImageRepository;
    }

    @Override
    @NotNull
    public Publisher<SimplifiedAlbumEntity> onAfterSave(@NotNull SimplifiedAlbumEntity entity,
                                                        @NotNull OutboundRow outboundRow,
                                                        @NotNull SqlIdentifier table) {

        return Flux.fromIterable(entity.getImageEntities())
                .doOnNext(image -> image.setAlbumId(entity.getId()))
                .flatMap(albumImageRepository::save)
                .then(Mono.just(entity));
    }
}
