package testing.asserts;

import com.odeyalo.sonata.miku.dto.SimplifiedTrackDto;
import com.odeyalo.sonata.miku.dto.SimplifiedTrackDtoContainer;
import org.assertj.core.api.AbstractAssert;

public class SimplifiedTrackDtoContainerAssert extends AbstractAssert<SimplifiedTrackDtoContainerAssert, SimplifiedTrackDtoContainer> {

    protected SimplifiedTrackDtoContainerAssert(SimplifiedTrackDtoContainer actual) {
        super(actual, SimplifiedTrackDtoContainerAssert.class);
    }

    public static SimplifiedTrackDtoContainerAssert forContainer(SimplifiedTrackDtoContainer actual) {
        return new SimplifiedTrackDtoContainerAssert(actual);
    }

    public SimplifiedTrackDtoContainerAssert length(int expectedLength) {
        if (actual.size() != expectedLength) {
            throw failureWithActualExpected(expectedLength, actual.size(), "The actual size of the container does not match");
        }
        return this;
    }


    public SimplifiedTrackDtoAssert peekFirst() {
        return peek(0);
    }

    public SimplifiedTrackDtoAssert peekSecond() {
        return peek(1);
    }

    private SimplifiedTrackDtoAssert peek(int index) {
        if (actual.size() <= index || index < 0) {
            throw failure("The index is greater than the actual size of the actual elements");
        }
        SimplifiedTrackDto track = actual.get(index);
        return SimplifiedTrackDtoAssert.forBody(track);
    }
}
