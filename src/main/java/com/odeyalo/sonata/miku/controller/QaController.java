package com.odeyalo.sonata.miku.controller;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.repository.*;
import com.odeyalo.sonata.miku.repository.r2dbc.delegate.R2dbcAlbumRepositoryDelegate;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.reactive.TransactionalOperator;
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
    private final SimplifiedAlbumRepository simplifiedAlbumRepository;
    private final R2dbcAlbumRepositoryDelegate albumRepository;
    private final AlbumArtistRepository albumArtistRepository;
    private final TransactionalOperator transactionalOperator;

    @PostMapping("/track")
    public Mono<TrackEntity> saveTrack(@RequestBody TrackEntity track) {
        return artistRepository.saveAll(track.getArtists()).collectList()
                .doOnNext(track::setArtists)
                .flatMap(artistEntities -> simplifiedAlbumRepository.save(track.getAlbum()))
                .flatMap(album -> saveTrack(track, album));
    }

    @DeleteMapping("/track/clear")
    public Mono<Void> clearTracks() {
        return trackArtistRepository.deleteAll()
                .then(albumArtistRepository.deleteAll())
                .then(trackRepository.deleteAll())
                .then(simplifiedAlbumRepository.deleteAll())
                .then(artistRepository.deleteAll());
    }

    @PostMapping("/album/simplified/")
    public Mono<SimplifiedAlbumEntity> saveSimplifiedAlbum(@RequestBody SimplifiedAlbumEntity album) {
        return simplifiedAlbumRepository.save(album);
    }

    @PostMapping("/album/")
    public Mono<AlbumEntity> saveAlbum(@RequestBody AlbumEntity album) {
        return albumRepository.save(album)
                .as(transactionalOperator::transactional);
    }

    @DeleteMapping("/album/clear")
    public Mono<Void> clearAlbums() {
        return trackArtistRepository.deleteAll()
                .then(albumArtistRepository.deleteAll())
                .then(trackRepository.deleteAll())
                .then(artistRepository.deleteAll())
                .then(simplifiedAlbumRepository.deleteAll());
    }

    private Mono<TrackEntity> saveTrack(@NotNull TrackEntity track, SimplifiedAlbumEntity albumEntity) {
        track.setAlbum(albumEntity);
        track.setAlbumId(albumEntity.getId());
        return trackRepository.save(track);
    }
}
