package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import com.odeyalo.sonata.miku.model.AlbumType;
import org.apache.commons.lang3.RandomStringUtils;

public class SimplifiedAlbumEntityFaker {
    private String publicId;
    private String name;
    private AlbumType albumType;

    final Faker faker = new Faker();

    public SimplifiedAlbumEntityFaker() {
        this.publicId = RandomStringUtils.randomAlphanumeric(22);
        this.name = faker.name().title();
        this.albumType = faker.options().option(AlbumType.class);
    }

    public static SimplifiedAlbumEntityFaker create() {
        return new SimplifiedAlbumEntityFaker();
    }

    public SimplifiedAlbumEntityFaker setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public SimplifiedAlbumEntityFaker setName(String name) {
        this.name = name;
        return this;
    }

    public SimplifiedAlbumEntityFaker setAlbumType(AlbumType albumType) {
        this.albumType = albumType;
        return this;
    }

    public SimplifiedAlbumEntity get() {
        return SimplifiedAlbumEntity.builder()
                .publicId(publicId)
                .name(name)
                .albumType(albumType)
                .build();
    }
}
