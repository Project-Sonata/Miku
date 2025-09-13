package com.odeyalo.sonata.miku.config.local;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import com.odeyalo.sonata.miku.model.AlbumType;
import com.odeyalo.sonata.miku.model.ReleaseDate;
import com.odeyalo.sonata.miku.repository.SimplifiedAlbumRepository;
import com.odeyalo.sonata.miku.repository.SimplifiedTrackRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@Profile("local")
public final class PredefinedAlbumsPreparator implements ApplicationRunner {
    private final SimplifiedAlbumRepository albumRepository;
    private final SimplifiedTrackRepository trackRepository;

    public PredefinedAlbumsPreparator(final SimplifiedAlbumRepository albumRepository,
                                      final SimplifiedTrackRepository trackRepository) {
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(final ApplicationArguments args) {
        var artist = ArtistEntity.builder().publicId("palth").name("Salvia Palth").build();

        var album = SimplifiedAlbumEntity.builder()
                .name("melanchole")
                .artist(artist)
                .publicId("album123")
                .albumType(AlbumType.SINGLE)
                .totalTracksCount(1)
                .releaseDate(ReleaseDate.onlyYear(2022))
                .build();

        album = albumRepository.save(album).block();

        var track = SimplifiedTrackEntity.builder()
                .albumId(album.getId())
                .name("melanchole")
                .durationMs(26052L)
                .publicId("m3lAn23")
                .streamingUri(URI.create("https://dtllfhd8mf8fr.cloudfront.net/m/3eTB1g3WnrR9YOtX1FXlMTMw2UIWX8AwY9hcCX9ZOAJ3XWelJb"))
                .artist(artist)
                .build();

        trackRepository.save(track).block();
    }
}
