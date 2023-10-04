package testing.asserts;

import com.odeyalo.sonata.miku.dto.AlbumDto;
import org.assertj.core.api.AbstractAssert;

public class AlbumDtoAssert extends AbstractAssert<AlbumDtoAssert, AlbumDto> {

    public AlbumDtoAssert(AlbumDto actual) {
        super(actual, AlbumDtoAssert.class);
    }

    protected AlbumDtoAssert(AlbumDto actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public static AlbumDtoAssert forAlbum(AlbumDto actual) {
        return new AlbumDtoAssert(actual);
    }

    public AlbumNameAssert name() {
        return AlbumNameAssert.of(actual.getName());
    }

    public IdAssert id() {
        return new IdAssert(actual.getId());
    }

    public AlbumTypeAssert albumType() {
        return new AlbumTypeAssert(actual.getAlbumType());
    }
}
