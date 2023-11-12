package testing.asserts;

import com.odeyalo.sonata.miku.dto.AlbumDto;
import org.assertj.core.api.AbstractAssert;

public class AlbumDtoAssert extends AbstractAssert<AlbumDtoAssert, AlbumDto> {

    public AlbumDtoAssert(AlbumDto actual) {
        super(actual, AlbumDtoAssert.class);
    }

    public static AlbumDtoAssert forAlbum(AlbumDto actual) {
        return new AlbumDtoAssert(actual);
    }

    public AlbumNameAssert name() {
        return AlbumNameAssert.of(actual.getName());
    }

    public IdAssert id() {
        return new IdAssert(actual.getId());
    }

    public AlbumTypeAssert albumType() {
        return new AlbumTypeAssert(actual.getAlbumType());
    }

    public AlbumDtoAssert totalTracks(int expectedCount) {
        if (actual.getTotalTracksCount() != expectedCount) {
            throw failureWithActualExpected(actual.getTotalTracksCount(), expectedCount, "Expected total tracks count to be equal!");
        }
        return this;
    }

    public SimplifiedTrackDtoContainerAssert tracks() {
        return new SimplifiedTrackDtoContainerAssert(actual.getTracks());
    }

    public ArtistsDtoAssert artists() {
        return new ArtistsDtoAssert(actual.getArtists());
    }

    public ReleaseDateAssert releaseDate() {
        return ReleaseDateAssert.forReleaseDate(actual.getReleaseDate());
    }
}
