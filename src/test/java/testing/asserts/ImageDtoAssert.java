package testing.asserts;

import com.odeyalo.sonata.miku.dto.ImageDto;
import org.assertj.core.api.AbstractAssert;

public final class ImageDtoAssert extends AbstractAssert<ImageDtoAssert, ImageDto> {

    private ImageDtoAssert(ImageDto imageDto) {
        super(imageDto, ImageDto.class);
    }

    public static ImageDtoAssert fromValue(ImageDto value) {
        return new ImageDtoAssert(value);
    }
}
