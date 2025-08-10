package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;

public class ApiSpec {

    // Получение API ключа из переменных окружения
    private static final String API_KEY = System.getProperty("api.key", "reqres-free-v1");

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setBasePath("/api")
            .addHeader("x-api-key", "reqres-free-v1")
            .setContentType(ContentType.JSON)
            .build();

    public static RequestSpecification requestWithBodySpec = with()
            .filter(withCustomTemplates())
            .contentType("application/json")
            .header("x-api-key", API_KEY);

    public static RequestSpecification postRequestSpec = requestWithBodySpec;
    public static RequestSpecification patchRequestSpec = requestWithBodySpec;
    public static RequestSpecification deleteRequestSpec = requestSpec;
    public static RequestSpecification loginRequestSpec = requestWithBodySpec;
    public static RequestSpecification registerRequestSpec = requestWithBodySpec;
    public static RequestSpecification getRequestSpec = requestSpec;

    public static ResponseSpecification successResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    public static ResponseSpecification createdResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectContentType(ContentType.JSON)
            .build();

    public static ResponseSpecification notFoundResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .expectContentType(ContentType.JSON)
            .build();

    public static ResponseSpecification noContentResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification badRequestResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectContentType(ContentType.JSON)
            .build();

    public static ResponseSpecification getSingleSpec = successResponseSpec;
    public static ResponseSpecification patchResponseSpec = successResponseSpec;
    public static ResponseSpecification postResponseSpec = createdResponseSpec;
    public static ResponseSpecification postResponseSpecNegative = notFoundResponseSpec;
    public static ResponseSpecification deleteResponseSpec = noContentResponseSpec;
    public static ResponseSpecification loginResponseSpec = createdResponseSpec;
    public static ResponseSpecification loginResponseSpecNegative = badRequestResponseSpec;
    public static ResponseSpecification registerResponseSpec = createdResponseSpec;
    public static ResponseSpecification registerResponseSpecNegative = badRequestResponseSpec;
}
