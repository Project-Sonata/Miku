package testing.asserts;

import org.assertj.core.api.AbstractStringAssert;

public final class TrackNameAssert extends AbstractStringAssert<TrackNameAssert> {

    public TrackNameAssert(String actual) {
        super(actual, TrackNameAssert.class);
    }

    public static TrackNameAssert forTrackName(String actual) {
        return new TrackNameAssert(actual);
    }
}
