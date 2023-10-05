package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.SimplifiedAlbumInfoDto;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SimplifiedAlbumInfoDtoConverter {

    @Mapping(target = "id", source = "publicId")
    SimplifiedAlbumInfoDto toSimplifiedAlbumInfoDto(SimplifiedAlbumEntity entity);

}
