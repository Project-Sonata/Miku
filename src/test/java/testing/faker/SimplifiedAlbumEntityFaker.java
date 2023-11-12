package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.model.AlbumType;
import com.odeyalo.sonata.miku.model.ReleaseDate;
import org.apache.commons.lang3.RandomStringUtils;

public class SimplifiedAlbumEntityFaker {
    private final SimplifiedAlbumEntity.SimplifiedAlbumEntityBuilder<?, ?> builder = SimplifiedAlbumEntity.builder();
    final Faker faker = new Faker();

    public SimplifiedAlbumEntityFaker() {
        builder.publicId(RandomStringUtils.randomAlphanumeric(22))
                .name(faker.name().title())
                .albumType(faker.options().option(AlbumType.class))
                .totalTracksCount(faker.random().nextInt(0, 10))
                .releaseDate(ReleaseDateFaker.randomReleaseDate().get());
    }

    public static SimplifiedAlbumEntityFaker create() {
        return new SimplifiedAlbumEntityFaker();
    }

    public SimplifiedAlbumEntityFaker setPublicId(String publicId) {
        this.builder.publicId(publicId);
        return this;
    }

    public SimplifiedAlbumEntityFaker setName(String name) {
        this.builder.name(name);
        return this;
    }

    public SimplifiedAlbumEntityFaker setAlbumType(AlbumType albumType) {
        this.builder.albumType(albumType);
        return this;
    }

    public SimplifiedAlbumEntityFaker setTotalTracks(int totalTracks) {
        this.builder.totalTracksCount(totalTracks);
        return this;
    }

    public SimplifiedAlbumEntityFaker releaseDate(ReleaseDate releaseDate) {
        builder.releaseDate(releaseDate);
        return this;
    }

    public SimplifiedAlbumEntity get() {
        return builder.build();
    }
}
