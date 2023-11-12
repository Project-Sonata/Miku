package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.model.ReleaseDate;
import com.odeyalo.sonata.miku.model.ReleaseDate.Precision;
import com.odeyalo.sonata.miku.support.converter.release.ReleaseDateDecoder;
import com.odeyalo.sonata.miku.support.converter.release.ReleaseDateInfo;
import com.odeyalo.sonata.suite.brokers.events.album.data.UploadedAlbumInfoDto;
import org.apache.commons.lang3.EnumUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {
        ArtistEntityContainerSuiteConverter.class,
        TrackEntityContainerSuiteConverter.class
}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class AlbumEntitySuiteConverter {

    @Autowired
    ReleaseDateDecoder<ReleaseDateInfo> releaseDateDecoder;

    @Mapping(target = "publicId", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract AlbumEntity toAlbumEntity(UploadedAlbumInfoDto source);


    @AfterMapping
    void enhanceWithReleaseDate(@MappingTarget AlbumEntity.AlbumEntityBuilder<?, ?> builder,
                                UploadedAlbumInfoDto source) {
        Precision precision = EnumUtils.getEnum(Precision.class, source.getReleaseDatePrecision());
        ReleaseDateInfo info = ReleaseDateInfo.of(source.getReleaseDateAsString(), precision);
        ReleaseDate releaseDate = releaseDateDecoder.decodeReleaseDate(info);

        builder.releaseDate(releaseDate);
    }


}
