package com.odeyalo.sonata.miku.controller;

import com.odeyalo.sonata.miku.dto.TrackDto;
import com.odeyalo.sonata.miku.repository.TrackRepository;
import com.odeyalo.sonata.miku.support.converter.TrackDtoConverter;
import com.odeyalo.sonata.miku.support.web.HttpStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/track")
public class TrackController {

    private final TrackRepository trackRepository;
    private final TrackDtoConverter trackDtoConverter;

    @Autowired
    public TrackController(TrackRepository trackRepository, TrackDtoConverter trackDtoConverter) {
        this.trackRepository = trackRepository;
        this.trackDtoConverter = trackDtoConverter;
    }

    @GetMapping(value = "/{trackId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<TrackDto>> fetchTrackById(@PathVariable String trackId) {
        return trackRepository.findByPublicId(trackId)
                .map(trackDtoConverter::toTrackDto)
                .map(HttpStatuses::ok)
                .defaultIfEmpty(HttpStatuses.unprocessableEntity());
    }
}
