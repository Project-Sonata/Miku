package com.odeyalo.sonata.miku.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/track")
public class TrackController {

    @GetMapping(value = "/{trackId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> fetchTrackById(@PathVariable String trackId) {
        return Mono.empty();
    }
}
