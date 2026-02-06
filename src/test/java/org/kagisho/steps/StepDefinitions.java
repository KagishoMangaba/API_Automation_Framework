package org.kagisho.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.kagisho.data.TestDataBuilder;
import org.kagisho.utilities.SpecUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class StepDefinitions extends SpecUtils {

    RequestSpecification requestSpecification; // class-level
    ResponseSpecification responseSpecification; // optional reusable
    TestDataBuilder data = new TestDataBuilder();
    Response response; // store response for later assertions

    @Given("Add Place Payload")
    public void add_place_payload() throws IOException {


        // Assign to class-level variable (no shadowing!)

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        requestSpecification = given().spec(requestSpecification())
                .body(data.addPlacePayLoad());
    }

    @When("user calls {string} with post http request")
    public void user_calls_with_post_http_request(String string) {
        // Use class-level requestSpecification
        response = given()
                .spec(requestSpecification)
                .when()
                .post("/maps/api/place/add/json"); // e.g., "maps/api/place/add/json"

        System.out.println(response.asString());
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
        String actualValue = response.jsonPath().getString(key);
        assertEquals(actualValue, expectedValue);
    }
}
