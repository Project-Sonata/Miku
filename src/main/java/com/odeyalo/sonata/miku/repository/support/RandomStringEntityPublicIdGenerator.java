package com.odeyalo.sonata.miku.repository.support;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RandomStringEntityPublicIdGenerator<T> implements EntityPublicIdGenerator<T> {
    private GenerationProperties generationProperties = DefaultGenerationProperties.getInstance();

    public RandomStringEntityPublicIdGenerator() {
    }

    public RandomStringEntityPublicIdGenerator(GenerationProperties generationProperties) {
        this.generationProperties = generationProperties;
    }

    @Override
    @NotNull
    public Mono<String> generatePublicId(@NotNull T entity) {
        int length = generationProperties.getLength();

        return Mono.just(RandomStringUtils.randomAlphabetic(length));
    }


    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Data
    @Builder
    public static class GenerationProperties {
        int length;

        public static GenerationProperties withLength(int length) {
            return GenerationProperties.builder().length(length).build();
        }
    }

    public static final class DefaultGenerationProperties extends GenerationProperties {
        public static final int DEFAULT_GENERATED_STRING_LENGTH = 22;
        private static final DefaultGenerationProperties instance = new DefaultGenerationProperties();

        public static DefaultGenerationProperties getInstance() {
            return instance;
        }

        private DefaultGenerationProperties() {
            super(DEFAULT_GENERATED_STRING_LENGTH);
        }
    }
}
