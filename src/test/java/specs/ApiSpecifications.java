package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class ApiSpecifications {
    
    private static final String API_KEY = System.getProperty("api.key", "reqres-free-v1");

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api")
            .header("x-api-key", "reqres-free-v1")
            .contentType(ContentType.JSON);

    public static RequestSpecification requestWithBodySpec = with()
            .filter(withCustomTemplates())
            .contentType("application/json")
            .header("x-api-key", API_KEY);

    public static RequestSpecification postRequestSpec = requestWithBodySpec;
    public static RequestSpecification patchRequestSpec = requestWithBodySpec;
    public static RequestSpecification loginRequestSpec = requestWithBodySpec;
    public static RequestSpecification registerRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api")
            .header("x-api-key", API_KEY)
            .contentType(ContentType.JSON);
    public static RequestSpecification getRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType("application/json")
            .header("x-api-key", API_KEY);

    public static ResponseSpecification successResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
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

    public static ResponseSpecification getSingleSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();
    public static ResponseSpecification patchResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();
    public static ResponseSpecification postResponseSpec = createdResponseSpec;
    public static ResponseSpecification postResponseSpecNegative = notFoundResponseSpec;
    public static ResponseSpecification loginResponseSpec = createdResponseSpec;
    public static ResponseSpecification registerResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectContentType(ContentType.JSON)
            .build();
}
