package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import reactor.test.StepVerifier;
import testing.sql.SqlScript;
import testing.sql.SqlScriptRunnerTestExecutionListener;

import java.util.Arrays;

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
    private ArtistRepository artistRepository;

    @Test
    @SqlScript(afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldFindByPublicId() {
        var entity = TrackEntity.builder()
                .publicId("something")
                .name("Cigarettes and coffee")
                .durationMs(123000L).build();

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
        var first = TrackEntity.builder().publicId("first").name("first_name").durationMs(1200L).build();
        var second = TrackEntity.builder().publicId("second").name("second_name").durationMs(2000L).build();

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
        var first = TrackEntity.builder().publicId("first").name("first_name").durationMs(1200L).build();
        var second = TrackEntity.builder().publicId("second").name("second_name").durationMs(2000L).build();

        insertTrackEntities(first, second);

        r2dbcTrackRepository.findAllByPublicIdIsIn()
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
    @SqlScript(afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldDeleteByPublicId() {
        var first = TrackEntity.builder().publicId("first").name("first_name").durationMs(1200L).build();
        var second = TrackEntity.builder().publicId("second").name("second_name").durationMs(2000L).build();

        insertTrackEntities(first, second);

        r2dbcTrackRepository.deleteByPublicId(first.getPublicId())
                .as(StepVerifier::create)
                .verifyComplete();

        r2dbcTrackRepository.findAll()
                .as(StepVerifier::create)
                .expectNext(second)
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
    @SqlScript(beforeTestExecutionLocations = "./sql/createArtistRow.sql",
            afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldSaveTrackWithArtist() {
        ArtistEntity existingArtist = ArtistEntity.builder().id(1L).publicId("public_artist_id").name("Bones").build();

        var track = TrackEntity.builder().publicId("miku").name("WhereTheTreesMeetsTheFreeway")
                .artist(existingArtist).durationMs(1200L).build();

        insertTrackEntities(track);

        r2dbcTrackRepository.findById(track.getId())
                .as(StepVerifier::create)
                .expectNextMatches(found -> !found.getArtists().isEmpty())
                .verifyComplete();
    }

    @Test
    @SqlScript(afterTestExecutionLocations = "./sql/clearTracksAndArtists.sql")
    void shouldSaveArtistOnMissingAndSaveTrack() {
        var bones = ArtistEntity.builder().publicId("bonesid").name("BONES").build();
        var baker = ArtistEntity.builder().publicId("eddyyyy").name("Eddy Baker").build();

        var track = TrackEntity.builder()
                .publicId("thereisanpublicid")
                .name("Money Mitch")
                .artist(bones)
                .artist(baker)
                .durationMs(100230L)
                .build();

        insertTrackEntities(track);

        artistRepository.findAllByPublicIdIn(bones.getPublicId(), baker.getPublicId())
                .as(StepVerifier::create)
                .expectNext(bones)
                .expectNext(baker)
                .verifyComplete();
    }

    private void insertTrackEntities(TrackEntity... entities) {
        this.r2dbcTrackRepository.saveAll(Arrays.asList(entities))
                .as(StepVerifier::create)
                .expectNextCount(entities.length)
                .verifyComplete();
    }
}