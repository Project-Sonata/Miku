package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.suite.brokers.events.album.data.ArtistContainerDto;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ArtistEntitySuiteConverter.class
})
public abstract class ArtistEntityContainerSuiteConverter {
    @Autowired
    ArtistEntitySuiteConverter artistDtoConverter;

    public List<ArtistEntity> toArtistEntities(ArtistContainerDto artists) {
        return artists.getArtists().stream()
                .map(artistDtoConverter::toArtistEntity)
                .toList();
    }
}
