package testing.asserts;

import com.odeyalo.sonata.miku.model.ReleaseDate;
import org.apache.commons.lang3.EnumUtils;
import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class ReleaseDateAssert extends AbstractAssert<ReleaseDateAssert, ReleaseDate> {

    protected ReleaseDateAssert(ReleaseDate releaseDate) {
        super(releaseDate, ReleaseDateAssert.class);
    }

    public static ReleaseDateAssert forReleaseDate(ReleaseDate actual) {
        return new ReleaseDateAssert(actual);
    }

    public ReleaseDateAssert hasPrecision(String expected) {
        if ( EnumUtils.isValidEnum(ReleaseDate.Precision.class, expected) ) {
            return this;
        }
        throw failure("The enum precision string is invalid: %s", expected);
    }

    public ReleaseDateAssert hasDay(int expected) {
        if ( Objects.equals(actual.getDay(), expected) ) {
            return this;
        }
        throw failureWithActualExpected(actual.getDay(), expected, "Day isn't equal");
    }

    public ReleaseDateAssert hasMonth(int expected) {
        if ( Objects.equals(actual.getMonth(), expected) ) {
            return this;
        }
        throw failureWithActualExpected(actual.getMonth(), expected, "Month isn't equal");
    }

    public ReleaseDateAssert hasYear(int expected) {
        if ( Objects.equals(actual.getYear(), expected) ) {
            return this;
        }
        throw failureWithActualExpected(actual.getYear(), expected, "Year isn't equal");
    }
}
