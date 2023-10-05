package testing.asserts;

import com.odeyalo.sonata.miku.dto.ArtistDto;
import com.odeyalo.sonata.miku.dto.ArtistsDto;
import org.assertj.core.api.AbstractAssert;

public class ArtistsDtoAssert extends AbstractAssert<ArtistsDtoAssert, ArtistsDto> {

    public ArtistsDtoAssert(ArtistsDto actual) {
        super(actual, ArtistsDtoAssert.class);
    }

    public static ArtistsDtoAssert forArtistsDto(ArtistsDto actual) {
        return new ArtistsDtoAssert(actual);
    }

    public ArtistsDtoAssert length(int expected) {
        if (actual.size() != expected) {
            throw failureWithActualExpected(actual.size(), expected, "Expected size of the artists to be equal");
        }
        return this;
    }

    public ArtistDtoAssert peekFirst() {
        return peek(0);
    }

    public ArtistDtoAssert peekSecond() {
        return peek(1);
    }

    public ArtistDtoAssert peekThird() {
        return peek(2);
    }

    public ArtistDtoAssert peek(int index) {
        if (index >= actual.size()) {
            throw failure("Index is greater than the actual size of the ArtistsDto");
        }
        ArtistDto peek = actual.get(index);
        return ArtistDtoAssert.forArtistDto(peek);
    }
}
