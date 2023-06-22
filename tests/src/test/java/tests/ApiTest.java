package tests;

import core.util.RetryAnalyzer;
import io.qameta.allure.Description;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

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

    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    @Description("GET API test verifies that code 200 returns")
    public void getTest() {

        String url = "v1/user";

        Response response = requestSpecification.request(Method.GET, url);

        assertEquals(response.jsonPath().getString("userRole"), "ADMINISTRATOR");
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    @Description("POST API test verifies that new user is created and code 201 returns")
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

        assertEquals(response.getStatusCode(), 201);
    }

    @Description("DELETE API test verifies that user is deleted and code 200 returns")
    @Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
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
