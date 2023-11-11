package com.odeyalo.sonata.miku.repository.r2dbc.support.callback.write;

import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import com.odeyalo.sonata.miku.repository.support.EntityPublicIdGenerator;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class TrackPublicIdGeneratorOnMissingBeforeConvertEntityCallback implements BeforeConvertCallback<SimplifiedTrackEntity> {
    private final EntityPublicIdGenerator<SimplifiedTrackEntity> publicIdGenerator;

    public TrackPublicIdGeneratorOnMissingBeforeConvertEntityCallback(EntityPublicIdGenerator<SimplifiedTrackEntity> publicIdGenerator) {
        this.publicIdGenerator = publicIdGenerator;
    }

    @Override
    @NotNull
    public Publisher<SimplifiedTrackEntity> onBeforeConvert(@NotNull SimplifiedTrackEntity entity,
                                                            @NotNull SqlIdentifier table) {
        if ( entity.getPublicId() == null ) {
            String publicId = RandomStringUtils.randomAlphanumeric(22);
            log.info("Missing public ID property for: {}. Generated public id: {}.", entity, publicId);
            entity.setPublicId(publicId);
        }
        return Mono.just(entity);
    }
}
