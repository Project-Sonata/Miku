package com.odeyalo.sonata.miku.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class FetchTrackByIdEndpointTest {

    @Autowired
    WebTestClient webTestClient;


    @Test
    void shouldReturnOkStatus() {
        WebTestClient.ResponseSpec responseSpec = fetchTrack();

        responseSpec.expectStatus().isOk();
    }

    private WebTestClient.ResponseSpec fetchTrack() {
        return webTestClient.get()
                .uri("/track/{trackId}", "something")
                .exchange();
    }
}
