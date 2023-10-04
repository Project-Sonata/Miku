package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.model.AlbumType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import testing.faker.ArtistEntityFaker;
import testing.faker.TrackEntityFaker;
import testing.sql.SqlScript;
import testing.sql.SqlScriptRunnerTestExecutionListener;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Tests for R2dbcTrackRepository
 */
@SpringBootTest
@ActiveProfiles("test")
@TestExecutionListeners(listeners = SqlScriptRunnerTestExecutionListener.class,
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class R2dbcTrackRepositoryTest {

    @Autowired
    R2dbcTrackRepository r2dbcTrackRepository;
    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SimplifiedAlbumRepository simplifiedAlbumRepository;

    @Test
    @SqlScript(afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldFindByPublicId() {
        var entity = TrackEntityFaker.create().get();
        insertTrackEntities(entity);

        r2dbcTrackRepository.findByPublicId(entity.getPublicId())
                .as(StepVerifier::create)
                .expectNext(entity)
                .verifyComplete();
    }

    @Test
    @SqlScript(afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldFindByPublicIdAndReturnEmpty() {
        r2dbcTrackRepository.findByPublicId("not_exist")
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
    @SqlScript(afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldFindAllByPublicIds() {
        var first = TrackEntityFaker.create().get();
        var second = TrackEntityFaker.create().get();

        insertTrackEntities(first, second);

        r2dbcTrackRepository.findAllByPublicIdIsIn(first.getPublicId(), second.getPublicId())
                .as(StepVerifier::create)
                .expectNext(first)
                .expectNext(second)
                .verifyComplete();
    }

    @Test
    @SqlScript(afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldFindAllByPublicIdsAndReturnNothingIfInputIsEmpty() {
        var first = TrackEntityFaker.create().get();
        var second = TrackEntityFaker.create().get();

        insertTrackEntities(first, second);

        r2dbcTrackRepository.findAllByPublicIdIsIn()
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
    @SqlScript(afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    @Disabled
    void shouldDeleteByPublicId() {
        var firstTrack = TrackEntityFaker.create().get();
        var secondTrack = TrackEntityFaker.create().get();

        insertTrackEntities(firstTrack, secondTrack);

        r2dbcTrackRepository.deleteByPublicId(secondTrack.getPublicId())
                .as(StepVerifier::create)
                .verifyComplete();

        r2dbcTrackRepository.findAll()
                .as(StepVerifier::create)
                .expectNextMatches(track -> track.getId().longValue() == firstTrack.getId().longValue())
                .verifyComplete();
    }

    @Test
    @SqlScript(beforeTestExecutionLocations = "./sql/singleTrackAndArtist.sql",
            afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldReturnArtist() {
        r2dbcTrackRepository.findByPublicId("trackuniqueid")
                .as(StepVerifier::create)
                .expectNextMatches(found -> !found.getArtists().isEmpty())
                .verifyComplete();
    }

    @Test
    @SqlScript(beforeTestExecutionLocations = "./sql/singleTrackAndArtist.sql"
            ,
            afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql"
    )
    void shouldReturnSimplifiedAlbumEntity() {
        r2dbcTrackRepository.findByPublicId("trackuniqueid")
                .as(StepVerifier::create)
                .expectNextMatches(found -> found.getAlbum() != null)
                .verifyComplete();
    }

    @Test
    @SqlScript(beforeTestExecutionLocations = "./sql/createArtistRow.sql",
            afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldSaveTrackWithArtist() {
        var track = TrackEntityFaker.create().get();

        insertTrackEntities(track);

        r2dbcTrackRepository.findById(track.getId())
                .as(StepVerifier::create)
                .expectNextMatches(found -> !found.getArtists().isEmpty())
                .verifyComplete();
    }

    @Test
    @SqlScript(afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldSaveArtistOnMissingAndSaveTrack() {
        var bones = ArtistEntityFaker.create().get();
        var baker = ArtistEntityFaker.create().get();

        var track = TrackEntityFaker.create()
                .setArtists(List.of(bones, baker))
                .get();

        insertTrackEntities(track);

        artistRepository.findAllByPublicIdIn(bones.getPublicId(), baker.getPublicId())
                .as(StepVerifier::create)
                .expectNext(bones)
                .expectNext(baker)
                .verifyComplete();
    }

    private void insertTrackEntities(TrackEntity... entities) {

        Flux.fromArray(entities)
                .flatMap(entity -> simplifiedAlbumRepository.save(entity.getAlbum()).doOnNext(album -> {
                    entity.setAlbumId(album.getId());
                    entity.setAlbum(album);
                }))
                .then().block();

        this.r2dbcTrackRepository.saveAll(Arrays.asList(entities))
                .as(StepVerifier::create)
                .expectNextCount(entities.length)
                .verifyComplete();
    }
}