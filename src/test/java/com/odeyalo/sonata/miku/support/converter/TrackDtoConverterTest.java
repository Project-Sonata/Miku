package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.TrackDto;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.faker.TrackEntityFaker;
import testing.spring.config.MapStructBeansBootstrapConfiguration;

import static testing.asserts.TrackDtoAssert.forTrack;

@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class TrackDtoConverterTest {

    @Autowired
    TrackDtoConverter converter;

    @Test
    void shouldConvertToValidTrackDto() {
        TrackEntity expected = getTrackEntity();
        TrackDto result = converter.toTrackDto(expected);

        forTrack(result).id().isEqualTo(expected.getPublicId());
        forTrack(result).album().isNotNull();
        forTrack(result).name().isEqualTo(expected.getName());
        forTrack(result).artists().length(result.getArtists().size());
        forTrack(result).duration().isEqualTo(result.getDurationMs());
    }

    @NotNull
    private static TrackEntity getTrackEntity() {
        TrackEntity trackEntity = TrackEntityFaker.create().setPublicId("hellooo").get();
        trackEntity.setId(10L);
        return trackEntity;
    }
}
