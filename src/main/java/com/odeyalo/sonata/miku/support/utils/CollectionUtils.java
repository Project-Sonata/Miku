package com.odeyalo.sonata.miku.support.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.HashSet;

/**
 * Utility class to work with collections
 */
@UtilityClass
public class CollectionUtils {
    /**
     * @param collection - collection to check
     * @return - true if the collection has at least one element. false otherwise
     */
    public boolean hasElements(Collection<?> collection) {
        return !collection.isEmpty();
    }

    /**
     * Checks if the two collections are equal despite order,
     * source = A, B, C, target = B, A, C - will return true
     * Rules applied for this method:
     * if {@param first} is null and {@param second} is null, then true is returned
     * if {@param first} is null and {@param second} is NOT null, then false is returned
     *
     * @param first  - first collection
     * @param second - second collection
     * @param <T>    - type of the collection
     * @return - {@code true} if collections are equal despite the order, {@code false} otherwise
     */
    public static <T> boolean areEqualDespiteOrder(Collection<T> first, Collection<T> second) {
        if ( first == null && second == null ) return true;
        if ( first == null ) return false;
        return new HashSet<>(first).containsAll(second) && first.size() == second.size();
    }
}
