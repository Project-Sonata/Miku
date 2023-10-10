package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.SimplifiedTrackDto;
import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ArtistsDtoConverter.class)
public interface SimplifiedTrackDtoConverter {

    @Mapping(source = "publicId", target = "id")
    SimplifiedTrackDto toSimplifiedTrackDto(SimplifiedTrackEntity source);

}
