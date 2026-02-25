package org.kagisho.base;

import io.restassured.response.Response;

public class TestContext {

    private String placeId;
    private Response response;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
