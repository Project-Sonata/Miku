package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.TrackDto;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        SimplifiedAlbumInfoDtoConverter.class,
        ArtistsDtoConverter.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TrackDtoConverter {

    @Mapping(target = "id", source = "publicId")
    @Mapping(target = "albumInfo", source = "album")
    TrackDto toTrackDto(TrackEntity target);
}
