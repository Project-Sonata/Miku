package testing.asserts;


import com.odeyalo.sonata.miku.model.AlbumType;
import org.assertj.core.api.AbstractAssert;

import static com.odeyalo.sonata.miku.model.AlbumType.*;

public class AlbumTypeAssert extends AbstractAssert<AlbumTypeAssert, AlbumType> {

    protected AlbumTypeAssert(AlbumType actual, Class<?> self) {
        super(actual, self);
    }

    public AlbumTypeAssert(AlbumType actual) {
        super(actual, AlbumTypeAssert.class);
    }

    public AlbumTypeAssert isSingle() {
        return is(SINGLE);
    }

    public AlbumTypeAssert isEpisode() {
        return is(EPISODE);
    }

    public AlbumTypeAssert isAlbum() {
        return is(ALBUM);
    }

    private AlbumTypeAssert is(AlbumType expected) {
        if (actual != expected) {
            throw failureWithActualExpected(actual, expected, "Expected album type to be equal to expected");
        }
        return this;
    }
}
