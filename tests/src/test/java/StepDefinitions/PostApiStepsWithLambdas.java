package StepDefinitions;

import io.cucumber.java8.En;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostApiStepsWithLambdas implements En {

    Response response;
    RequestSpecification requestSpecification;
    int index = new Random().nextInt(100000);
    String baseUrl = "http://localhost:8080/api/";
    String token = "8e893299-51f5-496b-85db-a86334bf9580";

    public PostApiStepsWithLambdas() {

        Given("API page is open with token1", () -> {
            requestSpecification =
                    given()
                            .headers("accept", "*/*",
                                    "Authorization", "bearer" + token,
                                    "Content-Type", "application/json")
                            .baseUri(baseUrl);
        });

        When("I use POST method with random index", () -> {
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

            response =
                    given(requestSpecification)
                            .body(requestBody)
                            .when()
                            .post(url)
                            .then()
                            .extract().response();
        });

        Then("Status code for POST is {int}", (Integer statusCode) -> {
            assertEquals(statusCode, response.getStatusCode());
        });

    }
}