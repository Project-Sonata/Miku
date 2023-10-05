package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.AlbumDto;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.faker.SimplifiedAlbumEntityFaker;
import testing.spring.config.MapStructBeansBootstrapConfiguration;

import static testing.asserts.AlbumDtoAssert.forAlbum;


@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class AlbumDtoConverterTest {

    @Autowired
    AlbumDtoConverter albumDtoConverter;

    @Test
    void toAlbumDto() {
        SimplifiedAlbumEntity album = getSimplifiedAlbumEntity();
        AlbumDto result = albumDtoConverter.toAlbumDto(album);

        forAlbum(result).id().isEqualTo(album.getPublicId());
        forAlbum(result).name().isEqualTo(album.getName());
        forAlbum(result).albumType().isEqualTo(album.getAlbumType());
    }

    @NotNull
    private static SimplifiedAlbumEntity getSimplifiedAlbumEntity() {
        SimplifiedAlbumEntity album = SimplifiedAlbumEntityFaker.create().get();
        album.setId(10L);
        return album;
    }
}