package testing.asserts;

import com.odeyalo.sonata.miku.dto.ArtistDto;
import org.assertj.core.api.AbstractAssert;

public class ArtistDtoAssert extends AbstractAssert<ArtistDtoAssert, ArtistDto> {

    public ArtistDtoAssert(ArtistDto actual) {
        super(actual, ArtistDtoAssert.class);
    }

    public static ArtistDtoAssert forArtistDto(ArtistDto actual) {
        return new ArtistDtoAssert(actual);
    }

    public IdAssert id() {
        return new IdAssert(actual.getId());
    }

    public ArtistNameAssert name() {
        return ArtistNameAssert.forName(actual.getName());
    }
}
