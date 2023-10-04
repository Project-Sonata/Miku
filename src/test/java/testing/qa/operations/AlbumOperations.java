package testing.qa.operations;

import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;

public interface AlbumOperations {

    SimplifiedAlbumEntity saveSimplifiedAlbum(SimplifiedAlbumEntity album);

    void clear();
}
