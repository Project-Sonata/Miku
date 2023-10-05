package com.odeyalo.sonata.miku.repository.support.delegate;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * Delegate interface that used to invoke SQL queries using R2DBC.
 * By default, configured by Spring.
 * Does not use the {@link org.springframework.r2dbc.core.DatabaseClient} directly but implementation can do it as well.
 */
@Repository
public interface R2dbcArtistRepositoryDelegate extends R2dbcRepository<ArtistEntity, Long> {
    /**
     * Search for all Artist entities with the given public ids
     *
     * @param ids - ids to search for, if the id does not exist, then nothing will be returned
     * @return - flux of the ArtistEntity associated with the given public ids
     */
    Flux<ArtistEntity> findAllByPublicIdIn(Collection<String> ids);

}
