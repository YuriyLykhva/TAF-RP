package tests;

import io.github.artsok.RepeatedIfExceptionsTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {

    /**
     * Variables, constants
     */
    String baseUrl = "http://localhost:8080/api/";

    String token = "8e893299-51f5-496b-85db-a86334bf9580";

    static int index = new Random().nextInt(100000);

    RequestSpecification requestSpecification =
            given()
                    .headers("accept", "*/*",
                            "Authorization", "bearer" + token,
                            "Content-Type", "application/json")
                    .baseUri(baseUrl);

    @RepeatedIfExceptionsTest(repeats = 3)
    @org.junit.Test
    public void getTest() {

        String url = "v1/user";

        Response response = requestSpecification.request(Method.GET, url);

        assertEquals("ADMINISTRATOR", response.jsonPath().getString("userRole"));
        assertEquals(200, response.getStatusCode());
    }

//    @RepeatedIfExceptionsTest(repeats = 3)
    @org.junit.Test
    public void postTest() {

        String url = "v1/user";

        String requestBody =
                String.format(
                        "{" +
                                "\"accountRole\": \"USER\", " +
                                "\"defaultProject\": \"superadmin_personal\",\n" +
                                "\"email\": \"user%d@email.com\",\n" +
                                "\"fullName\": \"string\",\n" +
                                "\"login\": \"user%d\",\n" +
                                "\"password\": \"string\",\n" +
                                "\"projectRole\": \"CUSTOMER\"\n" +
                                "}",
                        index, index);

        Response response =
                given(requestSpecification)
                        .body(requestBody)
                        .when()
                        .post(url)
                        .then()
                        .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @RepeatedIfExceptionsTest(repeats = 3)
    @org.junit.Test
    public void deleteTest() {

        String url = "v1/user";

        String requestBody = "{\"ids\": [" + index + "]}";

        given(requestSpecification)
                .body(requestBody)
                .when()
                .delete(url)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
