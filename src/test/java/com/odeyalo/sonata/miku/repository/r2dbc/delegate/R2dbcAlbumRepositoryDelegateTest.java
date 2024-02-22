package com.odeyalo.sonata.miku.repository.r2dbc.delegate;

import com.odeyalo.sonata.miku.entity.*;
import com.odeyalo.sonata.miku.repository.AlbumArtistRepository;
import com.odeyalo.sonata.miku.repository.RemoveCapable;
import com.odeyalo.sonata.miku.repository.TrackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;
import testing.asserts.ArtistEntityAssert;
import testing.faker.AlbumEntityFaker;
import testing.faker.ArtistEntityFaker;
import testing.faker.ImageEntityContainerFaker;
import testing.faker.SimplifiedTrackEntityFaker;

import java.util.List;
import java.util.Objects;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class R2dbcAlbumRepositoryDelegateTest {

    @Autowired
    R2dbcAlbumRepositoryDelegate r2dbcAlbumRepositoryDelegate;

    @Autowired
    RemoveCapable<ArtistEntity> artistEntityRemover;

    @Autowired
    AlbumArtistRepository albumArtistRepository;

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    R2dbcTrackArtistRepositoryDelegate r2dbcTrackArtistRepositoryDelegate;

    @Autowired
    RemoveCapable<AlbumImageEntity> albumImageEntityRemoveCapable;

    @AfterEach
    void tearDown() {
        albumImageEntityRemoveCapable.deleteAll()
                .then(albumArtistRepository.deleteAll())
                .then(r2dbcTrackArtistRepositoryDelegate.deleteAll())
                .then(trackRepository.deleteAll())
                .then(r2dbcAlbumRepositoryDelegate.deleteAll())
                .then(artistEntityRemover.deleteAll())
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
    void shouldReuseArtistIfTheArtistObjectsAreDifferentButPublicIdIsSame() {
        var artist = ArtistEntityFaker.create().get();
        var copiedArtist = ArtistEntity.from(artist);

        var track = SimplifiedTrackEntityFaker.create()
                .clearArtists()
                .artist(copiedArtist)
                .get();

        var album = AlbumEntityFaker.create()
                .clearArtists()
                .clearTracks()
                .publicId("water")
                .artist(artist)
                .track(track)
                .get();

        r2dbcAlbumRepositoryDelegate.save(album)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void shouldGeneratePublicIdIfNull() {
        var albumEntity = AlbumEntityFaker.create().publicId(null).get();
        r2dbcAlbumRepositoryDelegate.save(albumEntity)
                .as(StepVerifier::create)
                .expectNextMatches(Objects::nonNull)
                .verifyComplete();
    }

    @Test
    void shouldFindByPublicWithoutTracksAndArtistAndReturn() {

        var album = AlbumEntityFaker.create().get();

        insertAlbumEntities(album);

        r2dbcAlbumRepositoryDelegate.findByPublicId(album.getPublicId())
                .as(StepVerifier::create)
                .expectNext(album)
                .verifyComplete();
    }

    @Test
    void shouldSaveArtistsAndThenContainArtists() {

        var album = AlbumEntityFaker.create().get();

        insertAlbumEntities(album);

        r2dbcAlbumRepositoryDelegate.findByPublicId(album.getPublicId())
                .as(StepVerifier::create)
                .expectNextMatches(saved -> !saved.getArtists().isEmpty())
                .verifyComplete();
    }

    @Test
    void shouldSaveTracks() {
        var track = SimplifiedTrackEntityFaker.create().get();

        var album = AlbumEntityFaker.create().track(track).get();

        insertAlbumEntities(album);

        String[] ids = album.getTracks().stream().map(SimplifiedTrackEntity::getPublicId).toArray(String[]::new);

        trackRepository.findAllByPublicIdIsIn(ids)
                .as(StepVerifier::create)
                .expectNextCount(ids.length)
                .verifyComplete();
    }

    @Test
    void shouldSaveTracksWithArtists() {
        var artist = ArtistEntityFaker.create().get();

        var track = SimplifiedTrackEntityFaker.create()
                .clearArtists()
                .artist(artist)
                .get();

        var album = AlbumEntityFaker.create()
                .clearTracks()
                .clearArtists()
                .track(track)
                .artist(artist)
                .get();

        insertAlbumEntities(album);

        r2dbcAlbumRepositoryDelegate.findById(album.getId())
                .as(StepVerifier::create)
                .expectNextMatches(foundAlbum -> expectArtistToBePresentInTrack(artist, foundAlbum))
                .verifyComplete();
    }

    @Test
    void shouldSaveAlbumWithArtistsAndTrackWithAdditionalArtists() {
        var artist = ArtistEntityFaker.create().get();
        var additionalArtist = ArtistEntityFaker.create().get();

        var track = SimplifiedTrackEntityFaker.create()
                .clearArtists()
                .artist(artist)
                .artist(additionalArtist)
                .get();

        var album = AlbumEntityFaker.create()
                .clearTracks()
                .clearArtists()
                .track(track)
                .artist(artist)
                .get();

        insertAlbumEntities(album);

        r2dbcAlbumRepositoryDelegate.findById(album.getId())
                .as(StepVerifier::create)
                .expectNextMatches(foundAlbum -> {
                    SimplifiedTrackEntity firstTrack = foundAlbum.getTracks().get(0);
                    return firstTrack.getArtists().size() == 2;
                })
                .verifyComplete();
    }

    @Test
    void shouldSaveReleaseDate() {
        var album = AlbumEntityFaker.create().get();

        r2dbcAlbumRepositoryDelegate.save(album)
                .as(StepVerifier::create)
                .expectNextMatches(actual -> actual.getReleaseDate() != null)
                .verifyComplete();
    }

    @Test
    void shouldContainReleaseDateFieldOnSearching() {
        var album = AlbumEntityFaker.create().get();

        insertAlbumEntities(album);

        r2dbcAlbumRepositoryDelegate.findById(album.getId())
                .as(StepVerifier::create)
                .expectNextMatches(actual -> Objects.equals(album.getReleaseDate(), actual.getReleaseDate()))
                .verifyComplete();
    }

    @Test
    void findByNotExistingPublicIdAndReturnNothing() {
        r2dbcAlbumRepositoryDelegate.findByPublicId("not_existing")
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
    void shouldSaveAlbumWithImages() {
        AlbumEntity album = AlbumEntityFaker.create().get();

        ImageEntityContainer imageEntities = ImageEntityContainerFaker.withAmount(3).get();

        album.setImages(imageEntities);

        insertAlbumEntities(album);

        r2dbcAlbumRepositoryDelegate.findById(album.getId())
                .as(StepVerifier::create)
                .expectNext(album)
                .verifyComplete();
    }

    @Test
    void shouldSaveExactSameAlbumSize() {
        AlbumEntity album = AlbumEntityFaker.create().get();

        ImageEntityContainer imageEntities = ImageEntityContainerFaker.withAmount(3).get();

        album.setImages(imageEntities);

        insertAlbumEntities(album);

        r2dbcAlbumRepositoryDelegate.findById(album.getId())
                .as(StepVerifier::create)
                .expectNextMatches(saved -> saved.getImageEntities().size() == 3)
                .verifyComplete();
    }

    private static boolean expectArtistToBePresentInTrack(ArtistEntity artist, AlbumEntity foundAlbum) {
        SimplifiedTrackEntity firstTrack = foundAlbum.getTracks().get(0);

        ArtistEntity albumArtist = firstTrack.getArtists().get(0);

        ArtistEntityAssert.forEntity(albumArtist).publicId().isEqualTo(artist.getPublicId());
        ArtistEntityAssert.forEntity(albumArtist).name().isEqualTo(artist.getName());
        return true;
    }

    private void insertAlbumEntities(AlbumEntity... albums) {
        r2dbcAlbumRepositoryDelegate.saveAll(List.of(albums))
                .as(StepVerifier::create)
                .expectNextCount(albums.length)
                .verifyComplete();
    }
}