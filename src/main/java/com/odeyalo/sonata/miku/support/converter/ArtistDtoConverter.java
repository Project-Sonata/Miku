package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.ArtistDto;
import com.odeyalo.sonata.miku.entity.ArtistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtistDtoConverter {

    @Mapping(target = "id", source = "publicId")
    ArtistDto toArtistDto(ArtistEntity target);
}
