package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import java.util.Arrays;

/**
 * Tests for R2dbcTrackRepository
 */
@DataR2dbcTest
@ActiveProfiles("test")
class R2dbcTrackRepositoryTest {

    @Autowired
    R2dbcTrackRepository r2dbcTrackRepository;

    @AfterEach
    void tearDown() {
        r2dbcTrackRepository.deleteAll().block();
    }

    @Test
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
    void shouldFindByPublicIdAndReturnEmpty() {
        r2dbcTrackRepository.findByPublicId("not_exist")
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
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
    void shouldFindAllByPublicIdsAndReturnNothingIfInputIsEmpty() {
        var first = TrackEntity.builder().publicId("first").name("first_name").durationMs(1200L).build();
        var second = TrackEntity.builder().publicId("second").name("second_name").durationMs(2000L).build();

        insertTrackEntities(first, second);

        r2dbcTrackRepository.findAllByPublicIdIsIn()
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
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

    private void insertTrackEntities(TrackEntity... entities) {
        this.r2dbcTrackRepository.saveAll(Arrays.asList(entities))
                .as(StepVerifier::create)
                .expectNextCount(entities.length)
                .verifyComplete();
    }
}