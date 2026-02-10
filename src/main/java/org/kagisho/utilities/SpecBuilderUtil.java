package org.kagisho.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.equalTo;

public class SpecBuilderUtil {

    private static RequestSpecification req;

    public static RequestSpecification requestSpecification() throws FileNotFoundException {


        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        if (req == null) {
            req = new RequestSpecBuilder()
                    .setBaseUri(ConfigLoaderUtil.getProperties().getProperty("url"))
                    .addQueryParam("key", ConfigLoaderUtil.getProperties().getProperty("apikey"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON)
                    .build();
        return req;
        }
        return req;
    }




}

