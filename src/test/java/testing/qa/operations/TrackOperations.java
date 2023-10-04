package testing.qa.operations;

import com.odeyalo.sonata.miku.entity.TrackEntity;

/**
 * Interface to work with QA endpoint specific for track entity
 */
public interface TrackOperations {

    TrackEntity save(TrackEntity track);

    void clear();
}
