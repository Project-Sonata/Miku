package com.odeyalo.sonata.miku.support.converter.external;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.suite.brokers.events.album.data.SimplifiedTrackDtoContainer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class TrackEntityContainerSuiteConverter {
    @Autowired
    TrackEntitySuiteConverter trackEntitySuiteConverter;

    public TrackEntityContainerSuiteConverter() {
    }

    public List<TrackEntity> toTrackEntities(SimplifiedTrackDtoContainer container) {
        return container.getItems().stream()
                .map(trackEntitySuiteConverter::toSimplifiedTrackDto)
                .toList();
    }

    public void setTrackEntitySuiteConverter(TrackEntitySuiteConverter trackEntitySuiteConverter) {
        this.trackEntitySuiteConverter = trackEntitySuiteConverter;
    }
}
