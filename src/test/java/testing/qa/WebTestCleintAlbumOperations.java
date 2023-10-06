package testing.qa;

import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import testing.qa.operations.AlbumOperations;

public class WebTestCleintAlbumOperations implements AlbumOperations {
    private final WebTestClient webTestClient;

    public WebTestCleintAlbumOperations(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Override
    public SimplifiedAlbumEntity saveSimplifiedAlbum(SimplifiedAlbumEntity album) {
        return webTestClient.post()
                .uri("/qa/album/simplified/")
                .bodyValue(album)
                .exchange()
                .expectBody(SimplifiedAlbumEntity.class)
                .returnResult()
                .getResponseBody();
    }

    @Override
    public AlbumEntity saveAlbum(AlbumEntity album) {
        return webTestClient.post()
                .uri("/qa/album/")
                .bodyValue(album)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AlbumEntity.class)
                .returnResult()
                .getResponseBody();
    }

    @Override
    public void clear() {
        webTestClient.delete()
                .uri("/qa/album/clear")
                .exchange()
                .expectStatus().isOk();
    }
}
