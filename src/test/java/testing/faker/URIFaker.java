package testing.faker;

import java.net.URI;
import java.util.UUID;

public final class URIFaker {

    public static TrackUriFaker track() {
        return new TrackUriFaker();
    }

    public static final class TrackUriFaker {
        private final static String HOST = "https://cdn.sonata.com/track/";
        private final URI result;

        public TrackUriFaker() {
            this.result = URI.create(HOST + UUID.randomUUID());
        }

        public static TrackUriFaker create() {
            return new TrackUriFaker();
        }

        public URI get() {
            return result;
        }
    }
}
