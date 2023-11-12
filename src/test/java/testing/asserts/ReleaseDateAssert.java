package testing.asserts;

import com.odeyalo.sonata.miku.model.ReleaseDate;
import org.assertj.core.api.AbstractAssert;

public class ReleaseDateAssert extends AbstractAssert<ReleaseDateAssert, ReleaseDate> {

    protected ReleaseDateAssert(ReleaseDate releaseDate) {
        super(releaseDate, ReleaseDateAssert.class);
    }

    public static ReleaseDateAssert forReleaseDate(ReleaseDate actual) {
        return new ReleaseDateAssert(actual);
    }
}
