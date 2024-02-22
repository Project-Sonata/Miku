package testing.faker;


import com.odeyalo.sonata.miku.entity.AlbumImageEntity;
import com.odeyalo.sonata.miku.entity.ImageEntityContainer;

public final class ImageEntityContainerFaker {
    ImageEntityContainer.ImageEntityContainerBuilder builder = ImageEntityContainer.builder();

    private ImageEntityContainerFaker(int amount) {
        for (int i = 0; i < amount; i++) {
            AlbumImageEntity image = ImageEntityFaker.create().get();
            builder.image(image);
        }
    }

    public static ImageEntityContainerFaker withAmount(int amount) {
        return new ImageEntityContainerFaker(amount);
    }

    public ImageEntityContainer get() {
        return builder.build();
    }
}
