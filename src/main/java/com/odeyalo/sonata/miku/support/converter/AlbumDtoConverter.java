package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.AlbumDto;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlbumDtoConverter {

    @Mapping(target = "id", source = "publicId")
    AlbumDto toAlbumDto(SimplifiedAlbumEntity entity);
}
