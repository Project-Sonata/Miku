package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.SimplifiedTrackDto;
import com.odeyalo.sonata.miku.dto.SimplifiedTrackDtoContainer;
import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = SimplifiedTrackDtoConverter.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class SimplifiedTrackDtoContainerConverter {
    @Autowired
    SimplifiedTrackDtoConverter simplifiedTrackDtoConverter;

    public SimplifiedTrackDtoContainer toSimplifiedTrackDtoContainer(List<SimplifiedTrackDto> target) {
        return SimplifiedTrackDtoContainer.multiple(target);
    }

    public SimplifiedTrackDtoContainer entitiesToSimplifiedTrackDtoContainer(List<? extends SimplifiedTrackEntity> target) {
        List<SimplifiedTrackDto> tracks = toSimplifiedTracksDto(target);
        return toSimplifiedTrackDtoContainer(tracks);
    }

    @NotNull
    private List<SimplifiedTrackDto> toSimplifiedTracksDto(List<? extends SimplifiedTrackEntity> target) {
        return target.stream().map(simplifiedTrackDtoConverter::toSimplifiedTrackDto).toList();
    }
}
