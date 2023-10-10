package com.odeyalo.sonata.miku.support.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;


class CollectionUtilsTest {

    @Test
    void shouldReturnTrueIfTheCollectionIsNotEmpty() {
        boolean result = CollectionUtils.hasElements(List.of("something"));
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseIfTheCollectionIsEmpty() {
        boolean result = CollectionUtils.hasElements(emptyList());
        assertThat(result).isFalse();
    }
}