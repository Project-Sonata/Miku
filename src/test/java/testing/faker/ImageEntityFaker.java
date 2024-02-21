package testing.faker;

import com.github.javafaker.Faker;
import com.odeyalo.sonata.miku.entity.AlbumImageEntity;
import org.apache.commons.lang3.RandomStringUtils;

public final class ImageEntityFaker {
    AlbumImageEntity.AlbumImageEntityBuilder builder = AlbumImageEntity.builder();
    Faker faker = Faker.instance();

    public ImageEntityFaker(long albumId) {
        fakeImageEntity().albumId(albumId);
    }

    public ImageEntityFaker() {
        fakeImageEntity();
    }

    private AlbumImageEntity.AlbumImageEntityBuilder fakeImageEntity() {
        Integer widthHeight = faker.random().nextInt(50, 600);

        return builder
                .url("https://aws.s3.amazon.com/sonata/images/" + RandomStringUtils.randomAlphanumeric(16, 32))
                .width(widthHeight)
                .height(widthHeight);
    }

    public static ImageEntityFaker create(long albumId) {
        return new ImageEntityFaker(albumId);
    }

    public static ImageEntityFaker create() {
        return new ImageEntityFaker();
    }

    public AlbumImageEntity get() {
        return builder.build();
    }
}
