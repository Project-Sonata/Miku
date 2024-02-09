package testing.asserts;

import com.odeyalo.sonata.miku.dto.StreamableTrackUri;
import com.odeyalo.sonata.miku.dto.StreamableTrackUris;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.util.IterableUtil;

import java.util.Collection;

public final class StreamableTrackUrisAssert
        extends AbstractIterableAssert<StreamableTrackUrisAssert, StreamableTrackUris, StreamableTrackUri, StreamableTrackUriAssert> {

    public StreamableTrackUrisAssert(StreamableTrackUris streamableTrackUris) {
        super(streamableTrackUris, StreamableTrackUrisAssert.class);
    }

    public static StreamableTrackUrisAssert assertThat(StreamableTrackUris actual) {
        return new StreamableTrackUrisAssert(actual);
    }

    @Override
    protected StreamableTrackUriAssert toAssert(StreamableTrackUri value, String description) {
        return StreamableTrackUriAssert.assertThat(value);
    }

    @Override
    protected StreamableTrackUrisAssert newAbstractIterableAssert(Iterable<? extends StreamableTrackUri> iterable) {
        Collection<? extends StreamableTrackUri> collection = IterableUtil.toCollection(iterable);

        return new StreamableTrackUrisAssert(
                StreamableTrackUris.fromCollection(collection)
        );
    }

    public StreamableTrackUriAssert peekFirst() {
        return peek(0);
    }

    public StreamableTrackUriAssert peek(int index) {
        if ( actual.size() <= index ) {
            throw failureWithActualExpected(actual, index, "The index is greater than container size");
        }
        return StreamableTrackUriAssert.assertThat(actual.get(index));
    }
}
