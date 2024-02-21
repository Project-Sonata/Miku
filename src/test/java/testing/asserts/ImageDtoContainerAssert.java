package testing.asserts;

import com.odeyalo.sonata.miku.dto.ImageDto;
import com.odeyalo.sonata.miku.dto.ImageDtoContainer;
import com.odeyalo.sonata.miku.entity.ImageEntityContainer;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.util.IterableUtil;

import java.util.Collection;
import java.util.List;

public final class ImageDtoContainerAssert extends AbstractIterableAssert<ImageDtoContainerAssert, ImageDtoContainer, ImageDto, ImageDtoAssert> {

    private ImageDtoContainerAssert(ImageDtoContainer imageDtoContainer) {
        super(imageDtoContainer, ImageDtoContainerAssert.class);
    }

    public static ImageDtoContainerAssert create(ImageDtoContainer actual) {
        return new ImageDtoContainerAssert(actual);
    }

    @Override
    protected ImageDtoAssert toAssert(ImageDto value, String description) {
        return ImageDtoAssert.fromValue(value);
    }

    @Override
    protected ImageDtoContainerAssert newAbstractIterableAssert(Iterable<? extends ImageDto> iterable) {
        Collection<? extends ImageDto> images = IterableUtil.toCollection(iterable);
        return new ImageDtoContainerAssert(ImageDtoContainer.fromCollection(images));
    }

    public ImageDtoContainerAssert hasElements(ImageEntityContainer requiredEntities) {
        List<ImageDto> imageDtos = requiredEntities.stream().map(entity -> ImageDto.builder()
                .url(entity.getUrl())
                .width(entity.getWidth())
                .height(entity.getHeight())
                .build()).toList();

        containsAll(imageDtos);

        return this;
    }
}
