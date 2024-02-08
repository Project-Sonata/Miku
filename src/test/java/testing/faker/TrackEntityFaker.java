package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

/**
 * Faker to create fake data for TrackEntity
 */
public class TrackEntityFaker {
    private final Faker faker = new Faker();
    private final TrackEntity.TrackEntityBuilder<?, ?> builder = TrackEntity.builder();

    public TrackEntityFaker() {
        builder.publicId(RandomStringUtils.randomAlphanumeric(22))
                .name(faker.name().title())
                .durationMs(faker.random().nextLong())
                .album(SimplifiedAlbumEntityFaker.create().get())
                .artists(List.of(ArtistEntityFaker.create().get()))
                .streamingUri(URIFaker.track().get());
    }

    public static TrackEntityFaker create() {
        return new TrackEntityFaker();
    }

    public TrackEntity get() {
        return builder.build();
    }

    public TrackEntityFaker setPublicId(String publicId) {
        this.builder.publicId(publicId);
        return this;
    }

    public TrackEntityFaker setName(String name) {
        this.builder.name(name);
        return this;
    }

    public TrackEntityFaker setDurationMs(Long durationMs) {
        this.builder.durationMs(durationMs);
        return this;
    }

    public TrackEntityFaker setAlbum(SimplifiedAlbumEntity album) {
        this.builder.album(album);
        return this;
    }

    public TrackEntityFaker setArtists(List<ArtistEntity> artists) {
        this.builder.clearArtists();
        this.builder.artists(artists);
        return this;
    }
}
