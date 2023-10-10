package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.SimplifiedTrackDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.asserts.SimplifiedTrackDtoAssert;
import testing.faker.SimplifiedTrackEntityFaker;
import testing.spring.config.MapStructBeansBootstrapConfiguration;


@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class SimplifiedTrackDtoConverterTest {

    @Autowired
    SimplifiedTrackDtoConverter simplifiedTrackDtoConverter;

    @Test
    void toSimplifiedTrackDto() {
        var track = SimplifiedTrackEntityFaker.create().get();

        SimplifiedTrackDto result = simplifiedTrackDtoConverter.toSimplifiedTrackDto(track);

        SimplifiedTrackDtoAssert asserter = SimplifiedTrackDtoAssert.forBody(result);

        asserter.id().isEqualTo(track.getPublicId());
        asserter.name().isEqualTo(track.getName());
        asserter.duration().isEqualTo(track.getDurationMs());

        asserter.artists().isNotNull();

    }
}