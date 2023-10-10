package com.odeyalo.sonata.miku.entity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A simple interface that represent entity that can hold the multiple artists and can set them
 *
 * @see com.odeyalo.sonata.miku.repository.r2dbc.support.callback.read.AlbumArtistEnhancerAfterConvertCallback
 * @see com.odeyalo.sonata.miku.repository.r2dbc.support.callback.read.TrackArtistEnhancerAfterConvertCallback
 */
public interface ArtistsContainerHolder {

    @NotNull
    List<ArtistEntity> getArtists();

    void setArtists(List<ArtistEntity> artists);

}
