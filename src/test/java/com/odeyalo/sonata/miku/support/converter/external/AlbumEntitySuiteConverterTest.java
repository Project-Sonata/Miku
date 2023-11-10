package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.suite.brokers.events.album.data.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.spring.config.MapStructBeansBootstrapConfiguration;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class AlbumEntitySuiteConverterTest {

    @Autowired
    AlbumEntitySuiteConverter testable;

    @Test
    void toAlbumEntity() {
        var infoDto = createUploadedAlbumInfoDto();

        var result = testable.toAlbumEntity(infoDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNull();
        assertThat(result.getPublicId()).isNull();
        assertThat(result.getAlbumType().name()).isEqualTo(infoDto.getAlbumType().name());
        assertThat(result.getName()).isEqualTo(infoDto.getName());
        assertThat(result.getTotalTracksCount()).isEqualTo(infoDto.getTotalTracksCount());

        assertThat(result.getArtists()).isNotNull()
                .hasSize(infoDto.getArtists().size());


        assertThat(result.getTracks()).isNotNull()
                .hasSize(infoDto.getTracks().size());
    }

    private static UploadedAlbumInfoDto createUploadedAlbumInfoDto() {
        ArtistContainerDto artists = ArtistContainerDto.single(ArtistDto.of("id", "name"));
        SimplifiedTrackDtoContainer tracks = SimplifiedTrackDtoContainer.of(Collections.singletonList(
                SimplifiedTrackDto.builder()
                        .id("uniquetrackid")
                        .name("hello")
                        .durationMs(1488L)
                        .artists(artists)
                        .build()
        ));
        return UploadedAlbumInfoDto.builder()
                .id("helloooo")
                .totalTracksCount(10)
                .name("hello")
                .albumType(AlbumType.SINGLE)
                .artists(artists)
                .tracks(tracks)
                .releaseDatePrecision("YEAR")
                .releaseDateAsString("2023-08-03")
                .build();
    }
}