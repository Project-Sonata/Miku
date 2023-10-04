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
    private String publicId;
    private String name;
    private Long durationMs;
    private SimplifiedAlbumEntity album;
    private List<ArtistEntity> artists;

    final Faker faker = new Faker();

    public TrackEntityFaker() {
        this.publicId = RandomStringUtils.randomAlphanumeric(22);
        this.name = faker.name().title();
        this.durationMs = faker.random().nextLong();
        this.album = SimplifiedAlbumEntityFaker.create().get();
        this.artists = List.of(ArtistEntityFaker.create().get());
    }

    public static TrackEntityFaker create() {
        return new TrackEntityFaker();
    }

    public TrackEntityFaker setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public TrackEntityFaker setName(String name) {
        this.name = name;
        return this;
    }

    public TrackEntityFaker setDurationMs(Long durationMs) {
        this.durationMs = durationMs;
        return this;
    }

    public TrackEntityFaker setAlbum(SimplifiedAlbumEntity album) {
        this.album = album;
        return this;
    }

    public TrackEntityFaker setArtists(List<ArtistEntity> artists) {
        this.artists = artists;
        return this;
    }

    public TrackEntity get() {
        return TrackEntity.builder()
                .publicId(publicId)
                .name(name)
                .durationMs(durationMs)
                .album(album)
                .albumId(album.getId())
                .artists(artists)
                .build();
    }
}
