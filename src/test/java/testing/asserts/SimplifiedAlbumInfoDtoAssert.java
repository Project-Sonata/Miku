package testing.asserts;

import com.odeyalo.sonata.miku.dto.SimplifiedAlbumInfoDto;
import org.assertj.core.api.AbstractAssert;

public class SimplifiedAlbumInfoDtoAssert extends AbstractAssert<SimplifiedAlbumInfoDtoAssert, SimplifiedAlbumInfoDto> {

    public SimplifiedAlbumInfoDtoAssert(SimplifiedAlbumInfoDto actual) {
        super(actual, SimplifiedAlbumInfoDtoAssert.class);
    }

    public static SimplifiedAlbumInfoDtoAssert fromBody(SimplifiedAlbumInfoDto actual) {
        return new SimplifiedAlbumInfoDtoAssert(actual);
    }

    public IdAssert id() {
        return new IdAssert(actual.getId());
    }

    public AlbumNameAssert name() {
        return new AlbumNameAssert(actual.getName());
    }
}