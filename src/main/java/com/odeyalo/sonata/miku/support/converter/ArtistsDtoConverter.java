package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.ArtistDto;
import com.odeyalo.sonata.miku.dto.ArtistsDto;
import com.odeyalo.sonata.miku.entity.ArtistEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = ArtistDtoConverter.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ArtistsDtoConverter {

    @Autowired
    private ArtistDtoConverter artistDtoConverter;

    public ArtistsDto toArtistsDto(List<ArtistDto> target) {
        return ArtistsDto.multiple(target);
    }

    public ArtistsDto entitiesToArtistsDto(List<ArtistEntity> target) {
        List<ArtistDto> artists = target.stream().map(artistDtoConverter::toArtistDto).toList();
        return toArtistsDto(artists);
    }

    public ArtistsDtoConverter setArtistDtoConverter(ArtistDtoConverter artistDtoConverter) {
        this.artistDtoConverter = artistDtoConverter;
        return this;
    }
}
