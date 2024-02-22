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

    @Test
    void shouldReturnTrueForEqualCollectionsDespiteOrder() {
        List<Integer> first = List.of(1, 2, 3);
        List<Integer> second = List.of(3, 2, 1);

        boolean result = CollectionUtils.areEqualDespiteOrder(first, second);

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseForNotEqualCollectionsDespiteOrder() {
        List<Integer> first = List.of(1, 2, 3);
        List<Integer> second = List.of(4, 2, 1);

        boolean result = CollectionUtils.areEqualDespiteOrder(first, second);

        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseForNotEqualCollectionsWithDifferentSizeDespiteOrder() {
        List<Integer> first = List.of(1, 2, 3);
        List<Integer> second = List.of(4, 3, 2, 1);

        boolean result = CollectionUtils.areEqualDespiteOrder(first, second);

        assertThat(result).isFalse();
    }
}