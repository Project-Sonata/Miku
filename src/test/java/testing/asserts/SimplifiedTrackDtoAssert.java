package testing.asserts;

import com.odeyalo.sonata.miku.dto.SimplifiedTrackDto;
import org.assertj.core.api.AbstractAssert;

import static testing.asserts.TrackNameAssert.forTrackName;

public class SimplifiedTrackDtoAssert extends AbstractAssert<SimplifiedTrackDtoAssert, SimplifiedTrackDto> {

    protected SimplifiedTrackDtoAssert(SimplifiedTrackDto actual) {
        super(actual, SimplifiedTrackDtoAssert.class);
    }

    public static SimplifiedTrackDtoAssert forBody(SimplifiedTrackDto actual) {
        return new SimplifiedTrackDtoAssert(actual);
    }

    public IdAssert id() {
        return new IdAssert(actual.getId());
    }

    public TrackNameAssert name() {
        return forTrackName(actual.getName());
    }

    public TrackDurationAssert duration() {
        return new TrackDurationAssert(actual.getDurationMs());
    }

    public ArtistsDtoAssert artists() {
        return ArtistsDtoAssert.forArtistsDto(actual.getArtists());
    }
}
