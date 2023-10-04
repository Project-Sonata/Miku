package testing.asserts;

import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import org.assertj.core.api.AbstractAssert;

public class SimplifiedAlbumEntityAssert extends AbstractAssert<SimplifiedAlbumEntityAssert, SimplifiedAlbumEntity> {

    public SimplifiedAlbumEntityAssert(SimplifiedAlbumEntity actual) {
        super(actual, SimplifiedAlbumEntityAssert.class);
    }

    protected SimplifiedAlbumEntityAssert(SimplifiedAlbumEntity actual, Class<?> self) {
        super(actual, self);
    }

    public static SimplifiedAlbumEntityAssert fromEntity(SimplifiedAlbumEntity actual) {
        return new SimplifiedAlbumEntityAssert(actual);
    }


    public AlbumNameAssert name() {
        return new AlbumNameAssert(actual.getName());
    }
}
