package com.odeyalo.sonata.miku.support.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

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
}
