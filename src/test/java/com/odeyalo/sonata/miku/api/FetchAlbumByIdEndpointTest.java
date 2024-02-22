package com.odeyalo.sonata.miku.api;

import com.odeyalo.sonata.miku.dto.AlbumDto;
import com.odeyalo.sonata.miku.entity.AlbumEntity;
import com.odeyalo.sonata.miku.entity.ArtistEntity;
import com.odeyalo.sonata.miku.entity.SimplifiedTrackEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import testing.asserts.AlbumDtoAssert;
import testing.faker.AlbumEntityFaker;
import testing.faker.ArtistEntityFaker;
import testing.faker.SimplifiedTrackEntityFaker;
import testing.qa.AutoconfigureQaEnvironment;
import testing.qa.operations.QaOperations;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static testing.asserts.AlbumDtoAssert.forAlbum;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@AutoconfigureQaEnvironment
public class FetchAlbumByIdEndpointTest {
    @Autowired
    WebTestClient webTestClient;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    QaOperations qaOperations;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ExistingAlbumTests {
        private static final String ALBUM_ID = "water";
        AlbumEntity existingAlbum;

        @BeforeEach
        void setUp() {
            var artist = ArtistEntityFaker.create().get();

            var track = SimplifiedTrackEntityFaker.create()
                    .clearArtists()
                    .artist(artist)
                    .get();

            var album = AlbumEntityFaker.create()
                    .clearArtists()
                    .clearTracks()
                    .publicId(ALBUM_ID)
                    .artist(artist)
                    .track(track)
                    .get();

            existingAlbum = qaOperations.albums().saveAlbum(album);
            System.out.println("==========");
            System.out.println(existingAlbum);
            System.out.println("SAVED ALBUM");
        }

        @AfterEach
        void tearDown() {
            qaOperations.albums().clear();
        }

        @Test
        void shouldReturnOkStatus() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            responseSpec.expectStatus().isOk();
        }

        @Test
        void shouldReturnApplicationJsonContentType() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();
            responseSpec.expectHeader().contentType(APPLICATION_JSON);
        }

        @Test
        void shouldReturnAlbumId() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            forAlbum(responseBody).id().isEqualTo(ALBUM_ID);
        }

        @Test
        void shouldReturnAlbumName() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();
            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            forAlbum(responseBody).name().isEqualTo(existingAlbum.getName());
        }

        @Test
        void shouldReturnAlbumType() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            forAlbum(responseBody).albumType().isEqualTo(existingAlbum.getAlbumType());
        }

        @Test
        void shouldReturnAlbumReleaseDate() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            forAlbum(responseBody).releaseDate().isEqualTo(existingAlbum.getReleaseDate());
        }

        @Test
        void shouldReturnTotalTrackCount() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            forAlbum(responseBody).totalTracks(existingAlbum.getTotalTracksCount());
        }

        @Test
        void shouldReturnArtists() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            forAlbum(responseBody).artists().isNotNull();
        }

        @Test
        void shouldReturnArtistPublicIdInBody() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            ArtistEntity firstArtist = existingAlbum.getArtists().get(0);

            forAlbum(responseBody).artists().peekFirst().id().isEqualTo(firstArtist.getPublicId());
        }

        @Test
        void shouldReturnArtistPublicNameInBody() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            ArtistEntity firstArtist = existingAlbum.getArtists().get(0);

            forAlbum(responseBody).artists().peekFirst().name().isEqualTo(firstArtist.getName());
        }

        @Test
        void shouldReturnCoverImagesForAlbum() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            forAlbum(responseBody).images().hasSize(existingAlbum.getImageEntities().size());
        }

        @Test
        void shouldReturnCoverImagesSameAsSaved() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            forAlbum(responseBody).images().hasElements(existingAlbum.getImageEntities());
        }

        @Test
        void shouldReturnTracks() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            AlbumDtoAssert.forAlbum(responseBody).tracks().peekFirst().isNotNull();
        }

        @Test
        void shouldReturnTrackWithId() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            SimplifiedTrackEntity firstTrack = existingAlbum.getTracks().get(0);

            AlbumDtoAssert.forAlbum(responseBody).tracks().peekFirst().id().isEqualTo(firstTrack.getPublicId());
        }

        @Test
        void shouldReturnTrackWithName() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            SimplifiedTrackEntity firstTrack = existingAlbum.getTracks().get(0);

            AlbumDtoAssert.forAlbum(responseBody).tracks().peekFirst().name().isEqualTo(firstTrack.getName());
        }

        @Test
        void shouldReturnTrackWithArtist() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            SimplifiedTrackEntity firstTrack = existingAlbum.getTracks().get(0);

            AlbumDtoAssert.forAlbum(responseBody).tracks().peekFirst().artists().length(1);
        }

        @NotNull
        private WebTestClient.ResponseSpec fetchExistingAlbum() {
            return sendRequest(ALBUM_ID);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class NotExistingAlbumTests {

        @Test
        void shouldReturn422Status() {
            WebTestClient.ResponseSpec responseSpec = fetchNotExisting();

            responseSpec.expectStatus().isEqualTo(UNPROCESSABLE_ENTITY);
        }

        @NotNull
        private WebTestClient.ResponseSpec fetchNotExisting() {
            return sendRequest("not_existing");
        }
    }

    @NotNull
    private WebTestClient.ResponseSpec sendRequest(String albumId) {
        return webTestClient.get()
                .uri("/album/{albumId}", albumId)
                .exchange();
    }
}
