package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.suite.brokers.events.album.data.ArtistDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testing.spring.config.MapStructBeansBootstrapConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(MapStructBeansBootstrapConfiguration.class)
class ArtistEntitySuiteConverterTest {

    @Autowired
    ArtistEntitySuiteConverter testable;

    @Test
    void toArtistDto() {
        var artistDto = ArtistDto.of("miku", "Miku Nakano");

        ArtistEntity result = testable.toArtistEntity(artistDto);

        assertThat(result).isNotNull();
        assertThat(result.getPublicId()).isEqualTo("miku");
        assertThat(result.getName()).isEqualTo("Miku Nakano");
    }
}