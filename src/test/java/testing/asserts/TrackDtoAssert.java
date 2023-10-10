package testing.asserts;

import com.odeyalo.sonata.miku.dto.TrackDto;
import org.assertj.core.api.AbstractAssert;

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
        return TrackNameAssert.forTrackName(actual.getName());
    }

    public TrackDurationAssert duration() {
        return new TrackDurationAssert(actual.getDurationMs());
    }

    public ArtistsDtoAssert artists() {
        return new ArtistsDtoAssert(actual.getArtists());
    }

    public SimplifiedAlbumInfoDtoAssert album() {
        return new SimplifiedAlbumInfoDtoAssert(actual.getAlbumInfo());
    }

}