package org.kagisho.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.kagisho.data.TestDataBuilder;
import org.kagisho.resources.APIResources;
import org.kagisho.utilities.SpecBuilderUtil;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.kagisho.utilities.JsonPathUtil.getJsonPath;


public class AddPlace extends SpecBuilderUtil {

    RequestSpecification requestSpecification; // class-level
    ResponseSpecification responseSpecification; // optional reusable
    TestDataBuilder data = new TestDataBuilder();
    Response response; // store response for later assertions
    String placeId;
    JsonPath js;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {


        // Assign to class-level variable (no shadowing!)
        requestSpecification = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));


        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

    }

    @When("user calls {string} with post http request")
    public void user_calls_with_post_http_request(String string) {

        // Use class-level requestSpecification
        response = given()
                .spec(requestSpecification)
                .when()
                .post(APIResources.ADD_PLACE.getResource())
                .then().extract().response(); // e.g., "maps/api/place/add/json"


        placeId = response.jsonPath().getString("place_id");

    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(200, response.getStatusCode());

    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response , keyValue) , expectedValue);
    }


}
