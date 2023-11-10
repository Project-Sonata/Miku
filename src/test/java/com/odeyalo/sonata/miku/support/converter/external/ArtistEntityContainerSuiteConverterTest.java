package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.suite.brokers.events.album.data.ArtistContainerDto;
import com.odeyalo.sonata.suite.brokers.events.album.data.ArtistDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.spring.config.MapStructBeansBootstrapConfiguration;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class ArtistEntityContainerSuiteConverterTest {
    @Autowired
    ArtistEntityContainerSuiteConverter testable;

    @Test
    void shouldConvertSingleArtist() {
        var artistDto = ArtistDto.of("miku", "nakano");
        var container = ArtistContainerDto.single(artistDto);

        List<ArtistEntity> result = testable.toArtistEntities(container);

        assertThat(result).hasSize(1);

        assertThat(result).first().matches(actual -> {
            return Objects.equals(actual.getPublicId(), artistDto.getId()) &&
                    Objects.equals(actual.getName(), artistDto.getName());
        });
    }

    @Test
    void shouldConvertMultipleArtists() {
        var artistDto1 = ArtistDto.of("miku", "nakano");
        var artistDto2 = ArtistDto.of("Mashiro", "Shiina");
        List<ArtistEntity> expectedArtists = List.of(
                ArtistEntity.builder().publicId("miku").name("nakano").build(),
                ArtistEntity.builder().publicId("Mashiro").name("Shiina").build()
        );

        var container = ArtistContainerDto.of(List.of(artistDto1, artistDto2));

        List<ArtistEntity> result = testable.toArtistEntities(container);

        assertThat(result).hasSize(2);


        assertThat(result).containsAll(expectedArtists);

    }
}