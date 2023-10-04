package testing.qa.operations;

import com.odeyalo.sonata.miku.entity.TrackEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * TrackOperations implementation that used WebTestClient
 */
public class WebTestClientTrackOperations implements TrackOperations {
    private final WebTestClient webTestClient;

    public WebTestClientTrackOperations(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Override
    public TrackEntity save(TrackEntity track) {
        return webTestClient.post()
                .uri("/qa/track")
                .bodyValue(track)
                .exchange().expectBody(TrackEntity.class)
                .returnResult().getResponseBody();
    }

    @Override
    public void clear() {
        webTestClient.delete()
                .uri("/qa/track/clear")
                .exchange();
    }
}
