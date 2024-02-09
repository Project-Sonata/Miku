package com.odeyalo.sonata.miku.controller;

import com.odeyalo.sonata.miku.dto.TrackStreamingResponseDto;
import com.odeyalo.sonata.miku.repository.SimplifiedTrackRepository;
import com.odeyalo.sonata.miku.support.converter.TrackStreamingResponseDtoConverter;
import com.odeyalo.sonata.miku.support.web.HttpStatuses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/audio-storage")
@RequiredArgsConstructor
public final class AudioStorageController {
    private final SimplifiedTrackRepository repository;
    private final TrackStreamingResponseDtoConverter streamingResponseDtoConverter;


    @GetMapping("/{trackId}")
    public Mono<ResponseEntity<TrackStreamingResponseDto>> getStreamingUriById(@PathVariable String trackId) {
        return repository.findByPublicId(trackId)
                .map(streamingResponseDtoConverter::toTrackStreamingResponseDto)
                .map(HttpStatuses::ok)
                .defaultIfEmpty(HttpStatuses.badRequest());
    }
}
