package StepDefinitions;

import io.cucumber.java8.En;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetApiStepsWithLambdas implements En {

    Response response;
    RequestSpecification requestSpecification;
    String baseUrl = "http://localhost:8080/api/";
    String token = "8e893299-51f5-496b-85db-a86334bf9580";

    public GetApiStepsWithLambdas() {

        Given("API page is open with token", () -> {
            requestSpecification =
                    given()
                            .headers("accept", "*/*",
                                    "Authorization", "bearer" + token,
                                    "Content-Type", "application/json")
                            .baseUri(baseUrl);
        });

        When("I use GET method", () -> {
            String url = "v1/user";
            response = requestSpecification.request(Method.GET, url);
        });

        Then("Response of user role is Administrator", () -> {
            assertEquals("ADMINISTRATOR",
                    response.jsonPath().getString("userRole"));
        });

        And("Status code for GET is {int}", (Integer statusCode) -> {
            assertEquals(statusCode, response.getStatusCode());
        });
    }
}
