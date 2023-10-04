package testing.asserts;

import org.assertj.core.api.AbstractStringAssert;

public class AlbumNameAssert extends AbstractStringAssert<AlbumNameAssert> {

    public AlbumNameAssert(String actual) {
        super(actual, AlbumNameAssert.class);
    }

    protected AlbumNameAssert(String actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public static AlbumNameAssert of(String actual) {
        return new AlbumNameAssert(actual);
    }
}
