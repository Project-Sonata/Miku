package testing.asserts;

import com.odeyalo.sonata.miku.dto.ArtistsDto;
import org.assertj.core.api.AbstractAssert;

public class ArtistsDtoAssert extends AbstractAssert<ArtistsDtoAssert, ArtistsDto> {

    public ArtistsDtoAssert(ArtistsDto actual) {
        super(actual, ArtistsDtoAssert.class);
    }


    public ArtistsDtoAssert length(int expected) {
        if (actual.size() != expected) {
            throw failureWithActualExpected(actual.size(), expected, "Expected size of the artists to be equal");
        }
        return this;
    }
}
