package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetApiStepDefs {

    Response response;
    RequestSpecification requestSpecification;
    String baseUrl = "http://localhost:8080/api/";
    String token = "8e893299-51f5-496b-85db-a86334bf9580";

    @Given("API page is open with token")
    public void apiPageIsOpenWithToken() {

        requestSpecification =
                given()
                        .headers("accept", "*/*",
                                "Authorization", "bearer" + token,
                                "Content-Type", "application/json")
                        .baseUri(baseUrl);
    }

    @When("I use GET method")
    public void iUseGETMethod() {
        String url = "v1/user";
        response = requestSpecification.request(Method.GET, url);
    }

    @Then("Response of user role is Administrator")
    public void responseOfUserRoleIsAdministrator() {
        assertEquals("ADMINISTRATOR",
                response.jsonPath().getString("userRole"));
    }

    @And("Status code for GET is {int}")
    public void statusCodeIs(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

}
