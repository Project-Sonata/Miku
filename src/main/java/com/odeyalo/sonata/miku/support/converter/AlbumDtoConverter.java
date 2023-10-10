package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.AlbumDto;
import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                ArtistsDtoConverter.class,
                SimplifiedTrackDtoContainerConverter.class
        })
public interface AlbumDtoConverter {

    @Mapping(target = "id", source = "publicId")
    AlbumDto toAlbumDto(SimplifiedAlbumEntity entity);

    @Mapping(target = "id", source = "publicId")
    AlbumDto toAlbumDto(AlbumEntity entity);
}
