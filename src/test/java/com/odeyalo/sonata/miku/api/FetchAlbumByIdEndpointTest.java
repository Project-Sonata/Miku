package com.odeyalo.sonata.miku.api;

import com.odeyalo.sonata.miku.dto.AlbumDto;
import com.odeyalo.sonata.miku.entity.SimplifiedAlbumEntity;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import testing.asserts.AlbumDtoAssert;
import testing.faker.SimplifiedAlbumEntityFaker;
import testing.qa.AutoconfigureQaEnvironment;
import testing.qa.operations.QaOperations;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@AutoconfigureQaEnvironment
public class FetchAlbumByIdEndpointTest {
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    QaOperations qaOperations;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ExistingAlbumTests {
        private static final String ALBUM_ID = "water";
        SimplifiedAlbumEntity existingAlbum;

        @BeforeEach
        void setUp() {
            var album = SimplifiedAlbumEntityFaker.create().setPublicId(ALBUM_ID).get();

            existingAlbum = qaOperations.albums().saveSimplifiedAlbum(album);
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

            AlbumDtoAssert.forAlbum(responseBody).id().isEqualTo(ALBUM_ID);
        }

        @Test
        void shouldReturnAlbumName() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();
            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            AlbumDtoAssert.forAlbum(responseBody).name().isEqualTo(existingAlbum.getName());
        }

        @Test
        void shouldReturnAlbumType() {
            WebTestClient.ResponseSpec responseSpec = fetchExistingAlbum();

            AlbumDto responseBody = responseSpec.expectBody(AlbumDto.class).returnResult().getResponseBody();

            AlbumDtoAssert.forAlbum(responseBody).albumType().isEqualTo(existingAlbum.getAlbumType());
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
