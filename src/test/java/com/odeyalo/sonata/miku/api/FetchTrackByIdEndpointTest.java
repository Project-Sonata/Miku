package com.odeyalo.sonata.miku.api;

import com.odeyalo.sonata.miku.dto.TrackDto;
import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.entity.TrackEntity;
import com.odeyalo.sonata.miku.repository.ArtistRepository;
import com.odeyalo.sonata.miku.repository.TrackArtistRepository;
import com.odeyalo.sonata.miku.repository.TrackRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import testing.asserts.TrackDtoAssert;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class FetchTrackByIdEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    TrackArtistRepository trackArtistRepository;

    @AfterEach
    void tearDown() {
        trackArtistRepository.deleteAll().block();
        trackRepository.deleteAll().block();
        artistRepository.deleteAll().block();
    }

    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class FetchExistingTrack {
        public static final String TRACK_ID = "something";
        private TrackEntity existingTrack;

        @BeforeEach
        void setUp() {
            ArtistEntity artist = artistRepository.save(ArtistEntity.builder()
                    .publicId("artistid")
                    .name("Bones")
                    .build()).block();

            var track = TrackEntity.builder()
                    .publicId(TRACK_ID)
                    .name("U know it's understood")
                    .durationMs(10000L)
                    .artist(artist)
                    .build();

            existingTrack = trackRepository.save(track).block();
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
