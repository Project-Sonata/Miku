package testing.asserts;

import org.assertj.core.api.AbstractStringAssert;

public class ArtistNameAssert extends AbstractStringAssert<ArtistNameAssert> {

    public ArtistNameAssert(String actual) {
        super(actual, ArtistNameAssert.class);
    }

    public static ArtistNameAssert forName(String actual) {
        return new ArtistNameAssert(actual);
    }
}
