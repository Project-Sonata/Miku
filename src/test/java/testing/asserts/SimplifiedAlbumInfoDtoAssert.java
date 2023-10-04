package testing.asserts;

import com.odeyalo.sonata.miku.dto.SimplifiedAlbumInfoDto;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import org.assertj.core.api.AbstractAssert;

public class SimplifiedAlbumInfoDtoAssert extends AbstractAssert<SimplifiedAlbumInfoDtoAssert, SimplifiedAlbumInfoDto> {

    public SimplifiedAlbumInfoDtoAssert(SimplifiedAlbumInfoDto actual) {
        super(actual, SimplifiedAlbumInfoDtoAssert.class);
    }

    protected SimplifiedAlbumInfoDtoAssert(SimplifiedAlbumInfoDto actual, Class<?> self) {
        super(actual, self);
    }


    public static SimplifiedAlbumEntityAssert fromBody(SimplifiedAlbumEntity actual) {
        return new SimplifiedAlbumEntityAssert(actual);
    }

    public IdAssert id() {
        return new IdAssert(actual.getId());
    }

    public AlbumNameAssert name() {
        return new AlbumNameAssert(actual.getName());
    }
}