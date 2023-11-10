package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.suite.brokers.events.album.data.UploadedAlbumInfoDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        ArtistEntityContainerSuiteConverter.class,
        TrackEntityContainerSuiteConverter.class
}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AlbumEntitySuiteConverter {

    AlbumEntity toAlbumEntity(UploadedAlbumInfoDto source);

}
