package com.odeyalo.sonata.miku.api;

import com.odeyalo.sonata.miku.dto.TrackDto;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import testing.asserts.TrackDtoAssert;
import testing.faker.TrackEntityFaker;
import testing.qa.AutoconfigureQaEnvironment;
import testing.qa.operations.QaOperations;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureWebTestClient
@AutoconfigureQaEnvironment
@ActiveProfiles("test")
public class FetchTrackByIdEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    QaOperations qaOperations;

    @AfterEach
    void tearDown() {
        qaOperations.tracks().clear();
    }

    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class FetchExistingTrack {
        private static final String TRACK_ID = "something";
        private TrackEntity existingTrack;

        @BeforeEach
        void setUp() {
            var track = TrackEntityFaker.create().setPublicId(TRACK_ID).get();
            existingTrack = qaOperations.tracks().save(track);
        }

        @Test
        void shouldReturnOkStatus() {
            WebTestClient.ResponseSpec responseSpec = fetchTrack();

            responseSpec.expectStatus().isOk();
        }

        @Test
        void shouldReturnApplicationJsonContentType() {
            WebTestClient.ResponseSpec responseSpec = fetchTrack();

            responseSpec.expectHeader().contentType(APPLICATION_JSON);
        }

        @Test
        void shouldReturnParseableBody() {
            WebTestClient.ResponseSpec responseSpec = fetchTrack();

            TrackDto responseBody = responseSpec.expectBody(TrackDto.class).returnResult().getResponseBody();

            TrackDtoAssert.forTrack(responseBody).isNotNull();
        }

        @Test
        void shouldReturnTrackIdInResponseBody() {
            WebTestClient.ResponseSpec responseSpec = fetchTrack();

            TrackDto responseBody = responseSpec.expectBody(TrackDto.class).returnResult().getResponseBody();

            TrackDtoAssert.forTrack(responseBody).id().isEqualTo(TRACK_ID);
        }

        @Test
        void shouldReturnTrackName() {
            WebTestClient.ResponseSpec responseSpec = fetchTrack();

            TrackDto responseBody = responseSpec.expectBody(TrackDto.class).returnResult().getResponseBody();

            TrackDtoAssert.forTrack(responseBody).name().isEqualTo(existingTrack.getName());
        }

        @Test
        void shouldReturnTrackDuration() {
            WebTestClient.ResponseSpec responseSpec = fetchTrack();

            TrackDto responseBody = responseSpec.expectBody(TrackDto.class).returnResult().getResponseBody();

            TrackDtoAssert.forTrack(responseBody).duration().isEqualTo(existingTrack.getDurationMs());
        }

        @Test
        void shouldReturnTrackArtists() {
            WebTestClient.ResponseSpec responseSpec = fetchTrack();

            TrackDto responseBody = responseSpec.expectBody(TrackDto.class).returnResult().getResponseBody();

            TrackDtoAssert.forTrack(responseBody).artists().length(1);
        }

        private WebTestClient.ResponseSpec fetchTrack() {
            return sendRequest(TRACK_ID);
        }
    }

    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class FetchNotExistingTrack {

        @Test
        void shouldReturnUnprocessableEntityStatus() {
            WebTestClient.ResponseSpec responseSpec = fetchNotExistingTrack();

            responseSpec.expectStatus().isEqualTo(UNPROCESSABLE_ENTITY);
        }

        @NotNull
        private WebTestClient.ResponseSpec fetchNotExistingTrack() {
            return sendRequest("not_existing");
        }
    }

    private WebTestClient.ResponseSpec sendRequest(String trackId) {
        return webTestClient.get()
                .uri("/track/{trackId}", trackId)
                .exchange();
    }
}
