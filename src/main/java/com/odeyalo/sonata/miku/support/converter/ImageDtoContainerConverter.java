package com.odeyalo.sonata.miku.support.converter;


import com.odeyalo.sonata.miku.dto.ImageDto;
import com.odeyalo.sonata.miku.dto.ImageDtoContainer;
import com.odeyalo.sonata.miku.entity.ImageEntityContainer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = ImageDto.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ImageDtoContainerConverter {
    @Autowired
    ImageDtoConverter imageDtoConverter;

    public ImageDtoContainer fromAlbumImageContainer(ImageEntityContainer container) {
        List<ImageDto> imageDtos = container.stream().map(imageDtoConverter::fromAlbumImageEntity).toList();
        return ImageDtoContainer.fromCollection(imageDtos);
    }
}
