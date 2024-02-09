package testing.asserts;

import com.odeyalo.sonata.miku.dto.StreamableTrackUri;
import org.assertj.core.api.AbstractAssert;

import java.net.URI;
import java.util.Objects;

public final class StreamableTrackUriAssert extends AbstractAssert<StreamableTrackUriAssert, StreamableTrackUri> {

    private StreamableTrackUriAssert(StreamableTrackUri streamableTrackUri) {
        super(streamableTrackUri, StreamableTrackUriAssert.class);
    }

    public static StreamableTrackUriAssert assertThat(StreamableTrackUri value) {
        return new StreamableTrackUriAssert(value);
    }

    public StreamableTrackUriAssert hasFormat(String requiredFormat) {
        if ( Objects.equals(requiredFormat, actual.getFormat()) ) {
            return this;
        }
        throw failureWithActualExpected(actual.getFormat(), requiredFormat, "Streamable format mismatch");
    }

    public StreamableTrackUriAssert hasUri(String uriStr) {
        if ( Objects.equals(URI.create(uriStr), actual.getUri()) ) {
            return this;
        }
        throw failureWithActualExpected(actual.getUri().toString(), uriStr, "Streamable uri mismatch");
    }
}
