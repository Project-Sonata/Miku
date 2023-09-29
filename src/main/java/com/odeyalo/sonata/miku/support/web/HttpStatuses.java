package com.odeyalo.sonata.miku.support.web;

import org.springframework.http.ResponseEntity;

/**
 * Utility methods with different statuses to make code cleaner
 */
public final class HttpStatuses {

    public static <T> ResponseEntity<T> ok() {
        return ResponseEntity.ok().build();
    }

    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok().body(body);
    }

    public static <T> ResponseEntity<T> unprocessableEntity() {
        return ResponseEntity.unprocessableEntity().build();
    }

    public static <T> ResponseEntity<T> unprocessableEntity(T body) {
        return ResponseEntity.unprocessableEntity().body(body);
    }
}
