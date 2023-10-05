package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.ArtistDto;
import com.odeyalo.sonata.miku.dto.ArtistsDto;
import com.odeyalo.sonata.miku.entity.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.asserts.ArtistDtoAssert;
import testing.faker.ArtistDtoFaker;
import testing.faker.ArtistEntityFaker;
import testing.spring.config.MapStructBeansBootstrapConfiguration;

import java.util.List;

import static java.util.Collections.singletonList;
import static testing.asserts.ArtistsDtoAssert.forArtistsDto;

@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class ArtistsDtoConverterTest {

    @Autowired
    ArtistsDtoConverter artistsDtoConverter;

    @Test
    void singleArtistDtoAndExpectToBeConverted() {
        var artistDto = ArtistDtoFaker.create().get();
        ArtistsDto result = artistsDtoConverter.toArtistsDto(singletonList(artistDto));

        forArtistsDto(result).length(1);

        ArtistDtoAssert first = forArtistsDto(result).peekFirst();

        assertArtistDto(first, artistDto);
    }

    @Test
    void multipleArtistDtoAndExpectToBeConverted() {
        var artistDto1 = ArtistDtoFaker.create().get();
        var artistDto2 = ArtistDtoFaker.create().get();
        ArtistsDto result = artistsDtoConverter.toArtistsDto(List.of(artistDto1, artistDto2));

        forArtistsDto(result).length(2);

        ArtistDtoAssert first = forArtistsDto(result).peekFirst();
        ArtistDtoAssert second = forArtistsDto(result).peekSecond();

        assertArtistDto(first, artistDto1);
        assertArtistDto(second, artistDto2);
    }

    @Test
    void multipleEntitiesToArtistsDto() {
        var artistEntity1 = ArtistEntityFaker.create().get();
        var artistEntity2 = ArtistEntityFaker.create().get();

        ArtistsDto result = artistsDtoConverter.entitiesToArtistsDto(List.of(artistEntity1, artistEntity2));

        forArtistsDto(result).length(2);

        ArtistDtoAssert firstArtist = forArtistsDto(result).peekFirst();
        ArtistDtoAssert secondArtist = forArtistsDto(result).peekSecond();

        assertArtistDtoWithEntity(artistEntity1, firstArtist);

        assertArtistDtoWithEntity(artistEntity2, secondArtist);
    }

    private static void assertArtistDtoWithEntity(ArtistEntity artistEntity2, ArtistDtoAssert secondArtist) {
        secondArtist.id().isEqualTo(artistEntity2.getPublicId());
        secondArtist.name().isEqualTo(artistEntity2.getName());
    }

    private static void assertArtistDto(ArtistDtoAssert actual, ArtistDto expected) {
        actual.id().isEqualTo(expected.getId());
        actual.name().isEqualTo(expected.getName());
    }
}