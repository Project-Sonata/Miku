package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.net.URI;
import java.util.Collection;

public class SimplifiedTrackEntityFaker {
    private final SimplifiedTrackEntity.SimplifiedTrackEntityBuilder<?, ?> builder = SimplifiedTrackEntity.builder();

    private final Faker faker = Faker.instance();

    public SimplifiedTrackEntityFaker() {
        builder.name(faker.name().title())
                .publicId(RandomStringUtils.randomAlphanumeric(22))
                .durationMs(faker.random().nextLong())
                .artist(ArtistEntityFaker.create().get())
                .streamingUri(URI.create("https://aws.s3.odeyalo.com/sonata/tracks" + RandomStringUtils.randomAlphanumeric(22)));
    }

    public static SimplifiedTrackEntityFaker create() {
        return new SimplifiedTrackEntityFaker();
    }

    public SimplifiedTrackEntity get() {
        return builder.build();
    }

    public SimplifiedTrackEntityFaker clearArtists() {
        builder.clearArtists();
        return this;
    }

    public SimplifiedTrackEntityFaker artists(Collection<? extends ArtistEntity> artists) {
        builder.artists(artists);
        return this;
    }

    public SimplifiedTrackEntityFaker artist(ArtistEntity artist) {
        builder.artist(artist);
        return this;
    }

    public SimplifiedTrackEntityFaker albumId(Long albumId) {
        builder.albumId(albumId);
        return this;
    }

    public SimplifiedTrackEntityFaker durationMs(Long durationMs) {
        builder.durationMs(durationMs);
        return this;
    }

    public SimplifiedTrackEntityFaker name(String name) {
        builder.name(name);
        return this;
    }

    public SimplifiedTrackEntityFaker publicId(String publicId) {
        builder.publicId(publicId);
        return this;
    }

    public SimplifiedTrackEntityFaker id(Long id) {
        builder.id(id);
        return this;
    }
}
