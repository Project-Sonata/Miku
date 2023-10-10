package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import com.odeyalo.sonata.miku.model.AlbumType;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collection;

public class AlbumEntityFaker {

    private final AlbumEntity.AlbumEntityBuilder<?, ?> builder = AlbumEntity.builder();

    private int totalTracksCount = 0;

    private final Faker faker = Faker.instance();

    public AlbumEntityFaker() {
        builder
                .name(faker.name().title())
                .publicId(RandomStringUtils.randomAlphanumeric(22))
                .albumType(faker.options().option(AlbumType.class))
                .artist(ArtistEntityFaker.create().get())
                .totalTracksCount(totalTracksCount += 1);
    }


    public static AlbumEntityFaker create() {
        return new AlbumEntityFaker();
    }

    public AlbumEntity get() {
        return builder.build();
    }

    // Wrapper methods

    public AlbumEntityFaker track(SimplifiedTrackEntity track) {
        builder.totalTracksCount(totalTracksCount += 1);
        builder.track(track);
        return this;
    }

    public AlbumEntityFaker tracks(Collection<? extends SimplifiedTrackEntity> tracks) {
        builder.totalTracksCount(totalTracksCount += tracks.size());
        builder.tracks(tracks);
        return this;
    }

    public AlbumEntityFaker clearTracks() {
        totalTracksCount = 0;
        builder.totalTracksCount(0);
        builder.clearTracks();
        return this;
    }

    public AlbumEntityFaker id(Long id) {
        builder.id(id);
        return this;
    }

    public AlbumEntityFaker publicId(String publicId) {
        builder.publicId(publicId);
        return this;
    }

    public AlbumEntityFaker name(String name) {
        builder.name(name);
        return this;
    }

    public AlbumEntityFaker albumType(AlbumType albumType) {
        builder.albumType(albumType);
        return this;
    }

    public AlbumEntityFaker totalTracksCount(int totalTracksCount) {
        builder.totalTracksCount(totalTracksCount);
        return this;
    }

    public AlbumEntityFaker artist(ArtistEntity artist) {
        builder.artist(artist);
        return this;
    }

    public AlbumEntityFaker artists(Collection<? extends ArtistEntity> artists) {
        builder.artists(artists);
        return this;
    }

    public AlbumEntityFaker clearArtists() {
        builder.clearArtists();
        return this;
    }
}
