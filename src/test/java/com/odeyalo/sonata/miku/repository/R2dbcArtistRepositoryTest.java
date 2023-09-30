package com.odeyalo.sonata.miku.repository;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import java.util.Arrays;

@DataR2dbcTest
@TestInstance(Lifecycle.PER_METHOD)
@ActiveProfiles("test")
class R2dbcArtistRepositoryTest {
    @Autowired
    R2dbcArtistRepository r2dbcArtistRepository;

    @AfterEach
    void tearDown() {
        r2dbcArtistRepository.deleteAll().block();
    }

    @Test
    void shouldFindEverything() {
        var bones = ArtistEntity.builder().publicId("bonesid").name("BONES").build();
        var baker = ArtistEntity.builder().publicId("eddyyyy").name("Eddy Baker").build();

        insertArtistEntities(bones, baker);

        r2dbcArtistRepository.findAllByPublicIdIn(bones.getPublicId(), baker.getPublicId())
                .as(StepVerifier::create)
                .expectNext(bones)
                .expectNext(baker)
                .verifyComplete();
    }

    @Test
    void shouldReturnEmptyFluxIfIdNotExist() {
        r2dbcArtistRepository.findAllByPublicIdIn("not_exist", "again")
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
    void shouldReturnOnlyExistingValuesById() {
        var bones = ArtistEntity.builder().publicId("bonesid").name("BONES").build();

        insertArtistEntities(bones);

        r2dbcArtistRepository.findAllByPublicIdIn("not_exist", bones.getPublicId())
                .as(StepVerifier::create)
                .expectNext(bones)
                .verifyComplete();
    }

    private void insertArtistEntities(ArtistEntity... entities) {
        r2dbcArtistRepository.saveAll(Arrays.asList(entities))
                .as(StepVerifier::create)
                .expectNextCount(entities.length)
                .verifyComplete();
    }
}