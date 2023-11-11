package com.odeyalo.sonata.miku.service;

import com.odeyalo.sonata.miku.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;
import testing.faker.AlbumEntityFaker;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RepositoryDelegateAlbumSaverTest {
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    RepositoryDelegateAlbumSaver testable;


    @Test
    void shouldSaveInRepository() {
        var albumEntity = AlbumEntityFaker.create().get();

        testable.saveAlbum(albumEntity)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        albumRepository.findById(albumEntity.getId())
                .as(StepVerifier::create)
                .expectNext(albumEntity)
                .verifyComplete();
    }
}