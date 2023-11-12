package com.odeyalo.sonata.miku.repository.r2dbc.support.release.release;

/**
 * Central interface to convert the release date from and to database.
 *
 * @param <S> - source from convert to ReleaseDate
 * @param <T> - type to convert the ReleaseDate to
 */
public interface ReleaseDateConverter<S, T> extends ReleaseDateDecoder<S>, ReleaseDateEncoder<T> {
}
