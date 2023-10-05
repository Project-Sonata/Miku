package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.ArtistDto;
import com.odeyalo.sonata.miku.entity.ArtistEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.faker.ArtistEntityFaker;
import testing.spring.config.MapStructBeansBootstrapConfiguration;

import static testing.asserts.ArtistDtoAssert.forArtistDto;


@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class ArtistDtoConverterTest {

    @Autowired
    ArtistDtoConverter artistDtoConverter;

    @Test
    void toArtistDto() {
        ArtistEntity expected = getArtistEntity();

        ArtistDto result = artistDtoConverter.toArtistDto(expected);

        forArtistDto(result).id().isEqualTo(expected.getPublicId());
        forArtistDto(result).name().isEqualTo(expected.getName());
    }

    @NotNull
    private static ArtistEntity getArtistEntity() {
        ArtistEntity artistEntity = ArtistEntityFaker.create().get();
        artistEntity.setId(10L);
        return artistEntity;
    }
}