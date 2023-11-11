package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.suite.brokers.events.album.data.SimplifiedTrackDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = ArtistEntityContainerSuiteConverter.class)
public interface TrackEntitySuiteConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicId", ignore = true)
    TrackEntity toSimplifiedTrackDto(SimplifiedTrackDto track);

}
