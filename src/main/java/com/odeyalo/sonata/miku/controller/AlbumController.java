package com.odeyalo.sonata.miku.controller;


import com.odeyalo.sonata.miku.dto.AlbumDto;
import com.odeyalo.sonata.miku.repository.SimplifiedAlbumRepository;
import com.odeyalo.sonata.miku.support.web.HttpStatuses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private final SimplifiedAlbumRepository simplifiedAlbumRepository;

    public AlbumController(SimplifiedAlbumRepository simplifiedAlbumRepository) {
        this.simplifiedAlbumRepository = simplifiedAlbumRepository;
    }

    @GetMapping(value = "/{albumId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AlbumDto>> fetchAlbumById(@PathVariable String albumId) {
        return simplifiedAlbumRepository.findByPublicId(albumId)
                .map(album -> AlbumDto.builder()
                        .id(albumId)
                        .name(album.getName())
                        .albumType(album.getAlbumType())
                        .build())
                .map(HttpStatuses::ok)
                .defaultIfEmpty(HttpStatuses.unprocessableEntity());
    }
}
