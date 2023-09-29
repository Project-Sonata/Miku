package testing.asserts;

import org.assertj.core.api.AbstractLongAssert;

public class TrackDurationAssert extends AbstractLongAssert<TrackDurationAssert> {

    public TrackDurationAssert(Long actual) {
        super(actual, TrackDurationAssert.class);
    }
}
