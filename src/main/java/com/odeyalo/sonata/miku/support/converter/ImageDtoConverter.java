package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.ImageDto;
import com.odeyalo.sonata.miku.entity.AlbumImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageDtoConverter {

    ImageDto fromAlbumImageEntity(AlbumImageEntity albumImage);
}
