package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.AlbumDto;
import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.model.AlbumType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.asserts.AlbumDtoAssert;
import testing.faker.ArtistEntityFaker;
import testing.faker.SimplifiedAlbumEntityFaker;
import testing.faker.SimplifiedTrackEntityFaker;
import testing.spring.config.MapStructBeansBootstrapConfiguration;

import java.util.List;

import static testing.asserts.AlbumDtoAssert.forAlbum;


@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class AlbumDtoConverterTest {

    @Autowired
    AlbumDtoConverter albumDtoConverter;

    @Test
    void fromSimplifiedAlbumEntityToAlbumDto() {
        SimplifiedAlbumEntity album = getSimplifiedAlbumEntity();
        AlbumDto result = albumDtoConverter.toAlbumDto(album);

        AlbumDtoAssert albumDtoAsserter = forAlbum(result);

        albumDtoAsserter.id().isEqualTo(album.getPublicId());
        albumDtoAsserter.name().isEqualTo(album.getName());
        albumDtoAsserter.albumType().isEqualTo(album.getAlbumType());
        albumDtoAsserter.totalTracks(album.getTotalTracksCount());
    }

    @Test
    void fromAlbumEntityToAlbumDto() {
        AlbumEntity album = getAlbumEntity();

        AlbumDto result = albumDtoConverter.toAlbumDto(album);

        AlbumDtoAssert albumDtoAsserter = forAlbum(result);

        albumDtoAsserter.id().isEqualTo(album.getPublicId());
        albumDtoAsserter.name().isEqualTo(album.getName());
        albumDtoAsserter.albumType().isEqualTo(album.getAlbumType());

        albumDtoAsserter.totalTracks(album.getTotalTracksCount());
        albumDtoAsserter.tracks().length(1);
        albumDtoAsserter.artists().length(1);


    }

    private static AlbumEntity getAlbumEntity() {
        return AlbumEntity.builder()
                .publicId("fsafsas")
                .name("album name")
                .albumType(AlbumType.SINGLE)
                .totalTracksCount(1)
                .artists(List.of(ArtistEntityFaker.create().get()))
                .track(SimplifiedTrackEntityFaker.create().get())
                .build();
    }

    @NotNull
    private static SimplifiedAlbumEntity getSimplifiedAlbumEntity() {
        SimplifiedAlbumEntity album = SimplifiedAlbumEntityFaker.create().get();
        album.setId(10L);
        return album;
    }
}