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
import org.kagisho.models.Location;
import org.kagisho.models.Place;
import org.kagisho.utilities.JsonReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class StepDefinitions {

    RequestSpecification requestSpecification; // class-level
    ResponseSpecification responseSpecification; // optional reusable
    Response response; // store response for later assertions

    @Given("Add Place Payload")
    public void add_place_payload() throws IOException {
        // Read JSON payload into Place object
        Place place = JsonReaderUtil.readJson("data/AddPlace.json");

        // Set fields dynamically
        place.setAccuracy(50);
        place.setPhone_number("060 1234567");
        place.setWebsite("https://rahulshettyacademy.com");
        place.setAddress("161 Maude St, Sandown, Sandton, 2196");

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        place.setTypes(myList);

        Location location = new Location();
        location.setLat(232.11);
        location.setLng(12.332);
        place.setLocation(location);

        System.out.println(place);

        // Assign to class-level variable (no shadowing!)
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .setBody(place) // attach the payload here
                .build();

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
