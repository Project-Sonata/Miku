package testing.asserts;

import com.odeyalo.sonata.miku.entity.ArtistEntity;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.LongAssert;

public class ArtistEntityAssert extends AbstractAssert<ArtistEntityAssert, ArtistEntity> {

    public ArtistEntityAssert(ArtistEntity actual) {
        super(actual, ArtistEntityAssert.class);
    }

    public static ArtistEntityAssert forEntity(ArtistEntity actual) {
        return new ArtistEntityAssert(actual);
    }

    public LongAssert internalId() {
        return new LongAssert(actual.getId());
    }

    public IdAssert publicId() {
        return new IdAssert(actual.getPublicId());
    }

    public ArtistNameAssert name() {
        return new ArtistNameAssert(actual.getName());
    }
}