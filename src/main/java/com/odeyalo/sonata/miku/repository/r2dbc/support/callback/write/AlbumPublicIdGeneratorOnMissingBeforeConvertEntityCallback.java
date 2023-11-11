package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.write;

import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.repository.support.EntityPublicIdGenerator;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AlbumPublicIdGeneratorOnMissingBeforeConvertEntityCallback implements BeforeConvertCallback<SimplifiedAlbumEntity> {
    private final EntityPublicIdGenerator<SimplifiedAlbumEntity> entityPublicIdGenerator;

    @Autowired
    public AlbumPublicIdGeneratorOnMissingBeforeConvertEntityCallback(EntityPublicIdGenerator<SimplifiedAlbumEntity> entityPublicIdGenerator) {
        this.entityPublicIdGenerator = entityPublicIdGenerator;
    }

    @Override
    @NotNull
    public Publisher<SimplifiedAlbumEntity> onBeforeConvert(@NotNull SimplifiedAlbumEntity entity,
                                                            @NotNull SqlIdentifier table) {
        if ( entity.getPublicId() == null ) {
            return entityPublicIdGenerator.generatePublicId(entity)
                    .map(entity::setPublicId);
        }
        return Mono.just(entity);
    }
}
