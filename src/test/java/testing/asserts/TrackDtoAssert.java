package testing.asserts;

import com.odeyalo.sonata.miku.dto.TrackDto;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractStringAssert;

public class TrackDtoAssert extends AbstractAssert<TrackDtoAssert, TrackDto> {

    public TrackDtoAssert(TrackDto actual) {
        super(actual, TrackDtoAssert.class);
    }

    public static TrackDtoAssert forTrack(TrackDto actual) {
        return new TrackDtoAssert(actual);
    }

    public IdAssert id() {
        return new IdAssert(actual.getId());
    }

    public TrackNameAssert name() {
        return TrackNameAssert.forTrackName(actual.getName(), this);
    }

    public TrackDurationAssert duration() {
        return new TrackDurationAssert(actual.getDurationMs());
    }

    public ArtistsDtoAssert artists() {
        return new ArtistsDtoAssert(actual.getArtists());
    }

    public static final class TrackNameAssert extends AbstractStringAssert<TrackNameAssert> {
        private final TrackDtoAssert parent;

        public TrackNameAssert(String actual, TrackDtoAssert parent) {
            super(actual, TrackNameAssert.class);
            this.parent = parent;
        }

        public static TrackNameAssert forTrackName(String actual, TrackDtoAssert parent) {
            return new TrackNameAssert(actual, parent);
        }

        public TrackDtoAssert and() {
            return parent;
        }
    }
}