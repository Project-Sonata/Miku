package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.miku.dto.ArtistDto;
import org.apache.commons.lang3.RandomStringUtils;

public class ArtistDtoFaker {
    private String id;
    private String name;

    private final Faker faker = new Faker();

    public ArtistDtoFaker() {
        this.id = RandomStringUtils.randomAlphanumeric(22);
        this.name = faker.artist().name();
    }

    public static ArtistDtoFaker create() {
        return new ArtistDtoFaker();
    }

    public ArtistDtoFaker setId(String id) {
        this.id = id;
        return this;
    }

    public ArtistDtoFaker setName(String name) {
        this.name = name;
        return this;
    }

    public ArtistDto get() {
        return ArtistDto.builder()
                .id(id)
                .name(name)
                .build();
    }
}
