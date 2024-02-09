package com.odeyalo.sonata.miku.api;

import com.odeyalo.sonata.miku.dto.StreamableTrackUri;
import com.odeyalo.sonata.miku.dto.TrackStreamingResponseDto;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import testing.faker.AlbumEntityFaker;
import testing.faker.TrackEntityFaker;
import testing.qa.AutoconfigureQaEnvironment;
import testing.qa.operations.QaOperations;

import static testing.asserts.StreamableTrackUrisAssert.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureWebTestClient
@AutoconfigureQaEnvironment
@ActiveProfiles("test")
public class FetchTrackStreamingUriByIdEndpointTest {
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    QaOperations qaOperations;

    static final String EXISTING_TRACK_ID = "odeyalolovesmiku";
    static final TrackEntity EXISTING_TRACK = TrackEntityFaker.create().setPublicId(EXISTING_TRACK_ID).get();
    static final String NOT_EXISTING_TRACK_ID = "123";

    @BeforeEach
    void setup() {
        var album = AlbumEntityFaker.create().track(EXISTING_TRACK).get();
        qaOperations.albums().saveAlbum(album);
    }

    @AfterEach
    void clear() {
        qaOperations.albums().clear();
    }

    @Test
    void shouldReturn200OkStatusCodeForExistingUri() {
        WebTestClient.ResponseSpec responseSpec = getExistingStreamingUriForExistingTrackRequest();

        responseSpec.expectStatus().isOk();
    }

    @Test
    void shouldReturnApplicationJsonContentType() {
        WebTestClient.ResponseSpec responseSpec = getExistingStreamingUriForExistingTrackRequest();

        responseSpec.expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void shouldReturnSingleElementArrayResponse() {
        WebTestClient.ResponseSpec responseSpec = getExistingStreamingUriForExistingTrackRequest();

        TrackStreamingResponseDto responseBody = responseSpec.expectBody(TrackStreamingResponseDto.class).returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        assertThat(responseBody.getUris()).hasSize(1);
    }

    @Test
    void shouldReturnMp3FormatInResponse() {
        WebTestClient.ResponseSpec responseSpec = getExistingStreamingUriForExistingTrackRequest();

        TrackStreamingResponseDto responseBody = responseSpec.expectBody(TrackStreamingResponseDto.class).returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        assertThat(responseBody.getUris())
                .peekFirst().hasFormat(StreamableTrackUri.Formats.MP3_FILE);
    }

    @Test
    void shouldReturnStreamingUri() {
        WebTestClient.ResponseSpec responseSpec = getExistingStreamingUriForExistingTrackRequest();

        TrackStreamingResponseDto responseBody = responseSpec.expectBody(TrackStreamingResponseDto.class).returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        assertThat(responseBody.getUris())
                .peekFirst().hasUri(EXISTING_TRACK.getStreamingUri());
    }

    @Test
    void shouldReturnBadRequestForNotExistingTrackId() {
        WebTestClient.ResponseSpec responseSpec = getNotExistingStreamingUriForExistingTrackRequest();

        responseSpec.expectStatus().isBadRequest();
    }

    @NotNull
    private WebTestClient.ResponseSpec getExistingStreamingUriForExistingTrackRequest() {
        return webTestClient.get()
                .uri("/audio-storage/" + EXISTING_TRACK_ID)
                .exchange();
    }

    @NotNull
    private WebTestClient.ResponseSpec getNotExistingStreamingUriForExistingTrackRequest() {
        return webTestClient.get()
                .uri("/audio-storage/" + NOT_EXISTING_TRACK_ID)
                .exchange();
    }
}
