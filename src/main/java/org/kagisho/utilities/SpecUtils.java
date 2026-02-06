package org.kagisho.utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecUtils {

    private static RequestSpecification req;

    public static RequestSpecification requestSpecification() {
        if (req == null) {
            req = new RequestSpecBuilder()
                    .setBaseUri(ConfigLoaderUtil.getProperties().getProperty("url"))
                    .addQueryParam("key", ConfigLoaderUtil.getProperties().getProperty("apikey"))
                    .setContentType(ContentType.JSON)
                    .build();
        }
        return req;
    }




}

