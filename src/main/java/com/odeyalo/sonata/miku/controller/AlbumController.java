package com.odeyalo.sonata.miku.controller;


import com.odeyalo.sonata.miku.dto.AlbumDto;
import com.odeyalo.sonata.miku.repository.AlbumRepository;
import com.odeyalo.sonata.miku.support.converter.AlbumDtoConverter;
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
@RequestMapping("/album")
public class AlbumController {

    private final AlbumRepository albumRepository;
    private final AlbumDtoConverter albumDtoConverter;

    @Autowired
    public AlbumController(AlbumRepository albumRepository, AlbumDtoConverter albumDtoConverter) {
        this.albumRepository = albumRepository;
        this.albumDtoConverter = albumDtoConverter;
    }

    @GetMapping(value = "/{albumId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AlbumDto>> fetchAlbumById(@PathVariable String albumId) {
        return albumRepository.findByPublicId(albumId)
                .map(albumDtoConverter::toAlbumDto)
                .map(HttpStatuses::ok)
                .defaultIfEmpty(HttpStatuses.unprocessableEntity());
    }
}
