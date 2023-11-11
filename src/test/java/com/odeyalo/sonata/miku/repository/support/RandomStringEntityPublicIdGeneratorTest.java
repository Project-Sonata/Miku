package com.odeyalo.sonata.miku.repository.support;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.repository.support.RandomStringEntityPublicIdGenerator.DefaultGenerationProperties;
import com.odeyalo.sonata.miku.repository.support.RandomStringEntityPublicIdGenerator.GenerationProperties;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import testing.faker.TrackEntityFaker;

import static org.assertj.core.api.Assertions.assertThat;

class RandomStringEntityPublicIdGeneratorTest {

    @Test
    void shouldReturnNotNull() {
        var testable = new RandomStringEntityPublicIdGenerator<TrackEntity>();

        testable.generatePublicId(TrackEntityFaker.create().get())
                .as(StepVerifier::create)
                .expectNextMatches(StringUtils::isNotEmpty)
                .verifyComplete();
    }

    @Test
    void shouldUseDefaultConfigForGeneration() {
        var testable = new RandomStringEntityPublicIdGenerator<TrackEntity>();

        String generatedId = testable.generatePublicId(TrackEntityFaker.create().get()).block();

        assertThat(generatedId).hasSize(DefaultGenerationProperties.getInstance().getLength());
    }

    @Test
    void shouldUseCustomLengthForGeneration() {

        var testable = new RandomStringEntityPublicIdGenerator<TrackEntity>(
                GenerationProperties.withLength(10)
        );

        String generatedId = testable.generatePublicId(TrackEntityFaker.create().get()).block();

        assertThat(generatedId).hasSize(10);
    }
}