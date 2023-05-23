package tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import okhttp3.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WireMockTest {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private WireMockServer wireMockServer;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(8088);
        configureFor("localhost", 8088);
        wireMockServer.start();
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void getRequestWithEmptyResponseTest() throws IOException {

        stubFor(any((anyUrl())).willReturn(ok()));

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8088/users/1")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        assertEquals("", response.body().string());
        assertEquals(200, response.code());
        verify(exactly(1), getRequestedFor(urlEqualTo("/users/1")));
    }

    @Test
    public void getRequestWithBodyAndHeaderTest() throws IOException {
        configureStubsForGetMethod();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8088/users/1")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        assertEquals("Test success!", response.body().string());
        assertEquals("application/json", response.header("content-type"));
        verify(exactly(1), getRequestedFor(urlEqualTo("/users/1")));
    }

    private void configureStubsForGetMethod() {
        stubFor(get(urlEqualTo("/users/1"))
                .willReturn(ok("Test success!")
                        .withHeader("content-type", "application/json")));
    }

    @Test
    public void postRequestTest() throws IOException {

        stubFor(post(urlPathEqualTo("/my/resource"))
                .withRequestBody(equalToJson("{\n" +
                        "  \"value1\" : \"123\",\n" +
                        "  \"testValue\" : \"777\"\n" +
                        "}"))
                .willReturn(okJson("\"testValue\" : \"1\"")));

        OkHttpClient client = new OkHttpClient();

        String json = "{\n" +
                "  \"value1\" : \"123\",\n" +
                "  \"testValue\" : \"777\"\n" +
                "}";
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url("http://localhost:8088/my/resource")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals("\"testValue\" : \"1\"", response.body().string());
        assertEquals(200, response.code());
        verify(exactly(1), postRequestedFor(urlEqualTo("/my/resource")));

    }

    @Test
    public void postRequestWithJsonFileTest() throws IOException {

        stubFor(post(urlPathEqualTo("/body"))
                .withRequestBody(equalToJson("{\n" +
                        "  \"value1\" : \"123\",\n" +
                        "  \"testValue\" : \"777\"\n" +
                        "}"))
                .willReturn(ok("Literal text to put in the body")));

        OkHttpClient client = new OkHttpClient();

        File file = new File("src/test/resources/__files/test1.json");

        RequestBody body = RequestBody.create(JSON, file);

        Request request = new Request.Builder()
                .url("http://localhost:8088/body")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals("Literal text to put in the body", response.body().string());
        assertEquals(200, response.code());
        verify(exactly(1), postRequestedFor(urlEqualTo("/body")));
    }
}
