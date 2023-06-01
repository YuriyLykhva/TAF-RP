package tests.TestNG;

import core.model.User;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestNgApiTest {
    static User user = User.createUser();
    static String token = user.getToken();
    static int index = new Random().nextInt(100000);
    static int filterId, dashboardId;

    String baseUrl = "http://localhost:8080/api/v1/";

    RequestSpecification requestSpecification =
            given()
                    .auth().oauth2(token)
                    .proxy(8888)
                    .contentType(ContentType.JSON)
                    .baseUri(baseUrl);

    @Test(priority = 1)
    public void getUserTest() {

        String url = "user";

        Response response = requestSpecification.request(Method.GET, url);

        assertEquals(user.getRole(), response.jsonPath().getString("userRole"));
        assertEquals(response.getStatusCode(), 201);
    }

    @Test(priority = 1)
    public void getLaunchStatusTest() {

        String url = user.getUserProject() + "/launch/status";

        Response response = requestSpecification
                .param("ids", 6)
                .request(Method.GET, url);

        assertEquals(response.jsonPath().getString("6"), "PASSED");
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    public void getLaunchByIdTest() {

        String url = user.getUserProject() + "/launch/e696deee-5a9c-463a-9a87-9ae85ae3baba";

        Response response = requestSpecification.request(Method.GET, url);

        assertEquals("DEFAULT", response.jsonPath().getString("mode"));
        assertEquals("5", response.jsonPath().getString("statistics.executions.total"));
        assertTrue(5 > response.jsonPath().getInt("statistics.executions.failed"));
        assertEquals("superadmin", response.jsonPath().getString("owner"));
        assertEquals(200, response.getStatusCode());
    }

    @Test(priority = 1)
    public void getWidgetByIdTest() {

        String url = user.getUserProject() + "/widget/2";

        Response response = requestSpecification.request(Method.GET, url);

        assertEquals("LAUNCH STATISTICS AREA", response.jsonPath().getString("name"));
        assertEquals(200, response.getStatusCode());
    }

    @Test(priority = 1)
    public void createUserTest() {

        String url = "user";

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
                        .then().statusCode(201)
                        .extract().response();

        assertEquals(String.format("user%d", index), response.jsonPath().getString("login"));
    }

    @Test(priority = 1)
    public void createDashboardTest() {

        String url = user.getUserProject() + "/dashboard";

        String requestBody = String.format("{\n" +
                "  \"description\": \"string%d\",\n" +
                "  \"name\": \"string%d\",\n" +
                "  \"share\": true\n" +
                "}", index, index);

        Response response =
                given(requestSpecification)
                        .body(requestBody)
                        .when()
                        .post(url)
                        .then().statusCode(201)
                        .extract().response();

        dashboardId = response.jsonPath().getInt("id");
    }

    @Test(priority = 1)
    public void createFilterTest() {

        String url = user.getUserProject() + "/filter";

        String requestBody = String.format("{\n" +
                "  \"conditions\": [\n" +
                "    {\n" +
                "      \"condition\": \"has\",\n" +
                "      \"filteringField\": \"compositeAttribute\",\n" +
                "      \"value\": \"demo\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"description\": \"Some description\",\n" +
                "  \"name\": \"Another DEMO_FILTER%d\",\n" +
                "  \"orders\": [\n" +
                "    {\n" +
                "      \"isAsc\": false,\n" +
                "      \"sortingColumn\": \"startTime\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"share\": true,\n" +
                "  \"type\": \"launch\"\n" +
                "}", index);

        Response response =
                given(requestSpecification)
                        .body(requestBody)
                        .when()
                        .post(url)
                        .then().statusCode(201)
                        .extract().response();
        filterId = response.jsonPath().getInt("id");
    }

    @Test(priority = 3)
    public void deleteFilterTest() {

        String url = user.getUserProject() + "/filter/" + filterId;

        given(requestSpecification)
                .when()
                .delete(url)
                .then()
                .statusCode(200);
    }

    @Test(priority = 3)
    public void deleteDashboardTest() {

        String url = user.getUserProject() + "/dashboard/" + dashboardId;

        given(requestSpecification)
                .when()
                .delete(url)
                .then()
                .statusCode(200);
    }

    @Test(priority = 3)
    public void deleteUserTest() {

        String url = "user";

        String requestBody = "{\"ids\": [" + index + "]}";

        given(requestSpecification)
                .body(requestBody)
                .when()
                .delete(url)
                .then()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void updateWidgetTest() {

        String url = user.getUserProject() + "/widget/2";

        String requestBody = "{\n" +
                "  \"owner\": \"superadmin\",\n" +
                "  \"share\": true,\n" +
                "  \"id\": 2,\n" +
                "  \"name\": \"LAUNCH STATISTICS AREA\",\n" +
                "  \"widgetType\": \"statisticTrend\",\n" +
                "  \"contentParameters\": {\n" +
                "    \"contentFields\": [\n" +
                "      \"statistics$executions$total\",\n" +
                "      \"statistics$executions$passed\",\n" +
                "      \"statistics$executions$failed\",\n" +
                "      \"statistics$executions$skipped\",\n" +
                "      \"statistics$defects$product_bug$pb001\",\n" +
                "      \"statistics$defects$automation_bug$ab001\",\n" +
                "      \"statistics$defects$system_issue$si001\",\n" +
                "      \"statistics$defects$to_investigate$ti001\"\n" +
                "    ],\n" +
                "    \"itemsCount\": 50,\n" +
                "    \"widgetOptions\": {\n" +
                "      \"zoom\": false,\n" +
                "      \"timeline\": \"launch\",\n" +
                "      \"viewMode\": \"area-spline\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"appliedFilters\": [\n" +
                "    {\n" +
                "      \"owner\": \"superadmin\",\n" +
                "      \"share\": true,\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"DEMO_FILTER\",\n" +
                "      \"conditions\": [\n" +
                "        {\n" +
                "          \"filteringField\": \"compositeAttribute\",\n" +
                "          \"condition\": \"has\",\n" +
                "          \"value\": \"demo\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"orders\": [\n" +
                "        {\n" +
                "          \"sortingColumn\": \"startTime\",\n" +
                "          \"isAsc\": false\n" +
                "        }\n" +
                "      ],\n" +
                "      \"type\": \"Launch\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"content\": {\n" +
                "    \"result\": [\n" +
                "      {\n" +
                "        \"id\": 11,\n" +
                "        \"number\": 10,\n" +
                "        \"name\": \"Demo Api Tests\",\n" +
                "        \"startTime\": 1681121412291,\n" +
                "        \"values\": {\n" +
                "          \"statistics$executions$passed\": \"30\",\n" +
                "          \"statistics$executions$total\": \"30\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 10,\n" +
                "        \"number\": 9,\n" +
                "        \"name\": \"Demo Api Tests\",\n" +
                "        \"startTime\": 1681121410027,\n" +
                "        \"values\": {\n" +
                "          \"statistics$defects$automation_bug$ab001\": \"1\",\n" +
                "          \"statistics$defects$product_bug$pb001\": \"4\",\n" +
                "          \"statistics$defects$to_investigate$ti001\": \"2\",\n" +
                "          \"statistics$executions$failed\": \"5\",\n" +
                "          \"statistics$executions$passed\": \"20\",\n" +
                "          \"statistics$executions$total\": \"25\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 9,\n" +
                "        \"number\": 8,\n" +
                "        \"name\": \"Demo Api Tests\",\n" +
                "        \"startTime\": 1681121406023,\n" +
                "        \"values\": {\n" +
                "          \"statistics$defects$automation_bug$ab001\": \"4\",\n" +
                "          \"statistics$defects$product_bug$pb001\": \"4\",\n" +
                "          \"statistics$defects$to_investigate$ti001\": \"10\",\n" +
                "          \"statistics$executions$failed\": \"8\",\n" +
                "          \"statistics$executions$passed\": \"10\",\n" +
                "          \"statistics$executions$skipped\": \"2\",\n" +
                "          \"statistics$executions$total\": \"20\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 8,\n" +
                "        \"number\": 7,\n" +
                "        \"name\": \"Demo Api Tests\",\n" +
                "        \"startTime\": 1681121404179,\n" +
                "        \"values\": {\n" +
                "          \"statistics$defects$automation_bug$ab001\": \"5\",\n" +
                "          \"statistics$defects$product_bug$pb001\": \"1\",\n" +
                "          \"statistics$defects$system_issue$si001\": \"4\",\n" +
                "          \"statistics$defects$to_investigate$ti001\": \"8\",\n" +
                "          \"statistics$executions$failed\": \"9\",\n" +
                "          \"statistics$executions$passed\": \"5\",\n" +
                "          \"statistics$executions$skipped\": \"1\",\n" +
                "          \"statistics$executions$total\": \"15\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 7,\n" +
                "        \"number\": 6,\n" +
                "        \"name\": \"Demo Api Tests\",\n" +
                "        \"startTime\": 1681121402333,\n" +
                "        \"values\": {\n" +
                "          \"statistics$defects$automation_bug$ab001\": \"1\",\n" +
                "          \"statistics$defects$system_issue$si001\": \"8\",\n" +
                "          \"statistics$defects$to_investigate$ti001\": \"5\",\n" +
                "          \"statistics$executions$failed\": \"9\",\n" +
                "          \"statistics$executions$passed\": \"1\",\n" +
                "          \"statistics$executions$total\": \"10\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 6,\n" +
                "        \"number\": 5,\n" +
                "        \"name\": \"Demo Api Tests\",\n" +
                "        \"startTime\": 1681121348927,\n" +
                "        \"values\": {\n" +
                "          \"statistics$executions$passed\": \"30\",\n" +
                "          \"statistics$executions$total\": \"30\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 5,\n" +
                "        \"number\": 4,\n" +
                "        \"name\": \"Demo Api Tests\",\n" +
                "        \"startTime\": 1681121346299,\n" +
                "        \"values\": {\n" +
                "          \"statistics$defects$automation_bug$ab001\": \"1\",\n" +
                "          \"statistics$defects$product_bug$pb001\": \"4\",\n" +
                "          \"statistics$defects$to_investigate$ti001\": \"2\",\n" +
                "          \"statistics$executions$failed\": \"5\",\n" +
                "          \"statistics$executions$passed\": \"20\",\n" +
                "          \"statistics$executions$total\": \"25\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 4,\n" +
                "        \"number\": 3,\n" +
                "        \"name\": \"Demo Api Tests\",\n" +
                "        \"startTime\": 1681121343207,\n" +
                "        \"values\": {\n" +
                "          \"statistics$defects$automation_bug$ab001\": \"4\",\n" +
                "          \"statistics$defects$product_bug$pb001\": \"4\",\n" +
                "          \"statistics$defects$to_investigate$ti001\": \"10\",\n" +
                "          \"statistics$executions$failed\": \"8\",\n" +
                "          \"statistics$executions$passed\": \"10\",\n" +
                "          \"statistics$executions$skipped\": \"2\",\n" +
                "          \"statistics$executions$total\": \"20\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 3,\n" +
                "        \"number\": 2,\n" +
                "        \"name\": \"Demo Api Tests\",\n" +
                "        \"startTime\": 1681121340874,\n" +
                "        \"values\": {\n" +
                "          \"statistics$defects$automation_bug$ab001\": \"5\",\n" +
                "          \"statistics$defects$product_bug$pb001\": \"1\",\n" +
                "          \"statistics$defects$system_issue$si001\": \"4\",\n" +
                "          \"statistics$defects$to_investigate$ti001\": \"8\",\n" +
                "          \"statistics$executions$failed\": \"9\",\n" +
                "          \"statistics$executions$passed\": \"5\",\n" +
                "          \"statistics$executions$skipped\": \"1\",\n" +
                "          \"statistics$executions$total\": \"15\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"number\": 1,\n" +
                "        \"name\": \"Demo Api Tests\",\n" +
                "        \"startTime\": 1681121337740,\n" +
                "        \"values\": {\n" +
                "          \"statistics$defects$automation_bug$ab001\": \"1\",\n" +
                "          \"statistics$defects$system_issue$si001\": \"8\",\n" +
                "          \"statistics$defects$to_investigate$ti001\": \"5\",\n" +
                "          \"statistics$executions$failed\": \"9\",\n" +
                "          \"statistics$executions$passed\": \"1\",\n" +
                "          \"statistics$executions$total\": \"10\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        Response response =
                given(requestSpecification)
                        .body(requestBody)
                        .when()
                        .put(url)
                        .then()
                        .statusCode(200)
                        .extract().response();

        assertEquals(response.jsonPath().getString("message"),
                "Widget with ID = '2' successfully updated.");
    }
}
