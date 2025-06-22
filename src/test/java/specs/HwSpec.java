package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class HwSpec {

    public static RequestSpecification getRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType("application/json")
            .header("x-api-key", "reqres-free-v1")
            .log().all();

    public static RequestSpecification requestWithBodySpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .header("x-api-key", "reqres-free-v1")
            .log().all();

    public static RequestSpecification postRequestSpec = requestWithBodySpec;
    public static RequestSpecification patchRequestSpec = requestWithBodySpec;

    public static ResponseSpecification successResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification createdResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();

    public static ResponseSpecification notFoundResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(ALL)
            .build();

    public static ResponseSpecification getSingleSpec = successResponseSpec;
    public static ResponseSpecification patchResponseSpec = successResponseSpec;
    public static ResponseSpecification postResponseSpec = createdResponseSpec;
    public static ResponseSpecification postResponseSpecNegative = notFoundResponseSpec;
}