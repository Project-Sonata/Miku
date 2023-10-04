package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.miku.entity.ArtistEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.relational.core.mapping.Column;

public class ArtistEntityFaker {
    private String publicId;
    private String name;

    private final Faker faker = new Faker();

    public ArtistEntityFaker() {
        this.publicId = RandomStringUtils.randomAlphanumeric(22);
        this.name = faker.name().title();
    }

    public static ArtistEntityFaker create() {
        return new ArtistEntityFaker();
    }


    public ArtistEntityFaker setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public ArtistEntityFaker setName(String name) {
        this.name = name;
        return this;
    }

    public ArtistEntity get() {
        return ArtistEntity.builder()
                .publicId(publicId)
                .name(name)
                .build();
    }
}
