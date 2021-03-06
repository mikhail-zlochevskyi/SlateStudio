package com.slate.client;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.slate.config.impl.TestConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

@Slf4j
public abstract class RestClient {
    private RequestSpecification requestSpecification;

    public RestClient() {
        init();
    }

    private void init() {
        RestAssured.baseURI = "https://beta.todoist.com/API/v8";
        requestSpecification = new RequestSpecBuilder()
                .setContentType("application/json")
                .addHeader("Authorization","Bearer " + TestConfig.getUserToken())
                .log(LogDetail.ALL)
                .build();
    }

    private void logResponse(Response response) {
        response.then().log().all();
    }

    public <F> ResponseWrapper<F> get(String path, Class<F> responseClass) {
        Response response = given().spec(requestSpecification).get(path);
        logResponse(response);
        return new ResponseWrapper<>(response, responseClass);
    }

    public <T, F> ResponseWrapper<F> post(String path, Map payload, Class<F> responseClass) {
        Response response = given().spec(requestSpecification).queryParams(payload).post(path);
        logResponse(response);
        return new ResponseWrapper<>(response, responseClass);
    }

    public <T, F> ResponseWrapper<F> post(String path, Class<F> responseClass) {
        Response response = given().spec(requestSpecification).post(path);
        logResponse(response);
        return new ResponseWrapper<>(response, responseClass);
    }

    public <F> ResponseWrapper<F> delete(String path, Class<F> responseClass) {
        Response response = given().spec(requestSpecification).delete(path);
        logResponse(response);
        return new ResponseWrapper<>(response, responseClass);
    }
}