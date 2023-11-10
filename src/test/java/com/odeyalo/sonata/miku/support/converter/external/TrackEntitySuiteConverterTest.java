package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.suite.brokers.events.album.data.ArtistContainerDto;
import com.odeyalo.sonata.suite.brokers.events.album.data.ArtistDto;
import com.odeyalo.sonata.suite.brokers.events.album.data.SimplifiedTrackDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.spring.config.MapStructBeansBootstrapConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class TrackEntitySuiteConverterTest {

    @Autowired
    TrackEntitySuiteConverter testable;

    @Test
    void toSimplifiedTrackDto() {
        ArtistContainerDto artists = ArtistContainerDto.single(ArtistDto.of("id", "name"));
        var trackDto = SimplifiedTrackDto.builder()
                .name("hello")
                .id("something")
                .durationMs(1000L)
                .artists(artists)
                .build();

        TrackEntity result = testable.toSimplifiedTrackDto(trackDto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNull();
        assertThat(result.getPublicId()).isNull();
        assertThat(result.getName()).isEqualTo(trackDto.getName());
        assertThat(result.getDurationMs()).isEqualTo(trackDto.getDurationMs());

        assertThat(result.getArtists()).isNotNull()
                .hasSize(artists.size());

    }
}