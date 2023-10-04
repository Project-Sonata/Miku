package com.odeyalo.sonata.miku;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.model.AlbumType;
import com.odeyalo.sonata.miku.repository.ArtistRepository;
import com.odeyalo.sonata.miku.repository.SimplifiedAlbumRepository;
import com.odeyalo.sonata.miku.repository.TrackRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MikuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MikuApplication.class, args);
    }


//    @Bean
    public ApplicationRunner applicationRunner(TrackRepository trackRepository,
                                               SimplifiedAlbumRepository albumRepository,
                                               ArtistRepository artistRepository) {
        return (args) -> {
            var artist = ArtistEntity.builder().publicId("something").name("SHELTR.").build();
            artistRepository.save(artist).block();

            var albumEntity = SimplifiedAlbumEntity.builder()
                    .name("there is a name")
                    .publicId("unique_album_id")
                    .albumType(AlbumType.SINGLE)
                    .build();

            var album = albumRepository.save(albumEntity).block();

            TrackEntity entity = TrackEntity.builder()
                    .name("U know it's understood")
                    .publicId("unique id")
                    .durationMs(21412L)
                    .albumId(album.getId())
                    .build();
            trackRepository.save(entity).block();
        };
    }
}
