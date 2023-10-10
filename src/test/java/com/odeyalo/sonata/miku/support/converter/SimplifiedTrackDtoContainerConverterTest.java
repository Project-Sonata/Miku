package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.ArtistDto;
import com.odeyalo.sonata.miku.dto.ArtistsDto;
import com.odeyalo.sonata.miku.dto.SimplifiedTrackDto;
import com.odeyalo.sonata.miku.dto.SimplifiedTrackDtoContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.asserts.SimplifiedTrackDtoAssert;
import testing.asserts.SimplifiedTrackDtoContainerAssert;
import testing.faker.SimplifiedTrackEntityFaker;
import testing.spring.config.MapStructBeansBootstrapConfiguration;

import java.util.List;


@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class SimplifiedTrackDtoContainerConverterTest {

    @Autowired
    SimplifiedTrackDtoContainerConverter converter;

    @Test
    void toSimplifiedTrackDtoContainer() {
        SimplifiedTrackDto track = getSimplifiedTrackDto();

        SimplifiedTrackDtoContainer result = converter.toSimplifiedTrackDtoContainer(List.of(track));

        SimplifiedTrackDtoAssert trackDtoAsserter = SimplifiedTrackDtoContainerAssert.forContainer(result).peekFirst();

        trackDtoAsserter.id().isEqualTo(track.getId());
        trackDtoAsserter.name().isEqualTo(track.getName());
        trackDtoAsserter.duration().isEqualTo(track.getDurationMs());

        trackDtoAsserter.artists().isNotNull();
    }

    @Test
    void entitiesToSimplifiedTrackDtoContainer() {
        var track = SimplifiedTrackEntityFaker.create().get();

        SimplifiedTrackDtoContainer result = converter.entitiesToSimplifiedTrackDtoContainer(List.of(track));

        SimplifiedTrackDtoAssert trackDtoAsserter = SimplifiedTrackDtoContainerAssert.forContainer(result).peekFirst();

        trackDtoAsserter.id().isEqualTo(track.getPublicId());
        trackDtoAsserter.name().isEqualTo(track.getName());
        trackDtoAsserter.duration().isEqualTo(track.getDurationMs());

        trackDtoAsserter.artists().isNotNull();
    }

    private static SimplifiedTrackDto getSimplifiedTrackDto() {
        ArtistDto gigiMasin = ArtistDto.builder()
                .id("3w5lvcAoZ3CldAJ9astzC5")
                .name("Gigi Masin")
                .build();

        ArtistsDto artists = ArtistsDto.single(gigiMasin);

        return SimplifiedTrackDto.builder()
                .id("6I31DLt1hzqtI88uIX784R")
                .name("Clouds")
                .artists(artists)
                .durationMs(872421L)
                .build();
    }
}