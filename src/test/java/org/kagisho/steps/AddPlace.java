package org.kagisho.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.kagisho.base.TestContext;
import org.kagisho.data.TestDataBuilder;
import org.kagisho.resources.APIResources;
import org.kagisho.utilities.SpecBuilderUtil;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.kagisho.utilities.JsonPathUtil.getJsonPath;

public class AddPlace {

    private TestContext context;               // PicoContainer injects this
    private TestDataBuilder data = new TestDataBuilder();
    private RequestSpecification reqSpec;      // renamed for clarity

    // Constructor injection
    public AddPlace(TestContext context) {
        this.context = context;
    }

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        reqSpec = given()
                .spec(SpecBuilderUtil.requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        Response response;

        // dynamic method
        response = given()
                .spec(reqSpec)
                .when()
                .request(httpMethod.toUpperCase(), resourceAPI.getResource())
                .then()
                .extract()
                .response();

        // save response in context
        context.setResponse(response);

        // save placeId only if this is AddPlace API
        if (resource.equalsIgnoreCase("ADD_PLACE")) {
            String placeId = response.jsonPath().getString("place_id");
            context.setPlaceId(placeId);
        }
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer expectedStatus) {
        Response response = context.getResponse();
        assertEquals(expectedStatus.intValue(), response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
        Response response = context.getResponse();
        assertEquals(getJsonPath(response, key), expectedValue);
    }
}
