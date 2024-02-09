package com.odeyalo.sonata.miku.support.converter;

import com.odeyalo.sonata.miku.dto.StreamableTrackUri;
import com.odeyalo.sonata.miku.dto.StreamableTrackUris;
import com.odeyalo.sonata.miku.dto.TrackStreamingResponseDto;
import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import org.mapstruct.Mapper;

/**
 * Converts POJO to TrackStreamingResponseDto
 */
@Mapper(componentModel = "spring")
public interface TrackStreamingResponseDtoConverter {

    default TrackStreamingResponseDto toTrackStreamingResponseDto(SimplifiedTrackEntity track) {
        return TrackStreamingResponseDto.from(StreamableTrackUris.single(
                StreamableTrackUri.of(track.getStreamingUri(), StreamableTrackUri.Formats.MP3_FILE)
        ));
    }
}
