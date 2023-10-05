package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.SimplifiedAlbumInfoDto;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.asserts.SimplifiedAlbumInfoDtoAssert;
import testing.faker.SimplifiedAlbumEntityFaker;
import testing.spring.config.MapStructBeansBootstrapConfiguration;


@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class SimplifiedAlbumInfoDtoConverterTest {

    @Autowired
    SimplifiedAlbumInfoDtoConverter converter;

    @Test
    void toSimplifiedAlbumInfoDto() {
        SimplifiedAlbumEntity expected = getSimplifiedAlbumEntity();
        SimplifiedAlbumInfoDto result = converter.toSimplifiedAlbumInfoDto(expected);

        SimplifiedAlbumInfoDtoAssert.fromBody(result).name().isEqualTo(expected.getName());
        SimplifiedAlbumInfoDtoAssert.fromBody(result).id().isEqualTo(expected.getPublicId());
    }

    @NotNull
    private static SimplifiedAlbumEntity getSimplifiedAlbumEntity() {
        SimplifiedAlbumEntity entity = SimplifiedAlbumEntityFaker.create().get();
        entity.setId(10L);
        return entity;
    }
}