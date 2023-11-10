package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.suite.brokers.events.album.data.ArtistDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtistEntitySuiteConverter {

    @Mapping(target = "publicId", source = "id")
    @Mapping(target = "id", ignore = true)
    ArtistEntity toArtistEntity(ArtistDto artist);
}
