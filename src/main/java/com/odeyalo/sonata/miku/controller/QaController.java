package com.odeyalo.sonata.miku.controller;

import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.repository.ArtistRepository;
import com.odeyalo.sonata.miku.repository.SimplifiedAlbumRepository;
import com.odeyalo.sonata.miku.repository.TrackArtistRepository;
import com.odeyalo.sonata.miku.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Rest controller that used only for QA and tests
 */
@RestController
@RequestMapping("/qa")
@RequiredArgsConstructor
@Profile(value = {"test", "qa"})
public class QaController {
    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;
    private final TrackArtistRepository trackArtistRepository;
    private final SimplifiedAlbumRepository albumRepository;

    @PostMapping("/track")
    public Mono<TrackEntity> saveTrack(@RequestBody TrackEntity track) {
        return artistRepository.saveAll(track.getArtists()).collectList()
                .doOnNext(track::setArtists)
                .flatMap(artistEntities -> albumRepository.save(track.getAlbum()))
                .flatMap(album -> saveTrack(track, album));
    }

    @DeleteMapping("/track/clear")
    public Mono<Void> clearTracks() {
        return trackArtistRepository.deleteAll()
                .then(trackRepository.deleteAll()
                        .then(albumRepository.deleteAll()
                                .then(artistRepository.deleteAll())));
    }

    private Mono<TrackEntity> saveTrack(@NotNull TrackEntity track, SimplifiedAlbumEntity albumEntity) {
        track.setAlbum(albumEntity);
        track.setAlbumId(albumEntity.getId());
        return trackRepository.save(track);
    }
}
