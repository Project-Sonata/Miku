package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.suite.brokers.events.album.data.UploadedAlbumInfoDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ArtistEntityContainerSuiteConverter.class,
        TrackEntityContainerSuiteConverter.class
}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AlbumEntitySuiteConverter {

    @Mapping(target = "publicId", ignore = true)
    @Mapping(target = "id", ignore = true)
    AlbumEntity toAlbumEntity(UploadedAlbumInfoDto source);

}
