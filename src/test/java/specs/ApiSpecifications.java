package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class ApiSpecifications {
    
    private static final String API_KEY = System.getProperty("api.key", "reqres-free-v1");

    public static RequestSpecification patchRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType("application/json")
            .header("x-api-key", API_KEY);
            
    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType("application/json")
            .header("x-api-key", API_KEY);
            
    public static RequestSpecification registerRequestSpec = with()
            .filter(withCustomTemplates())
            .header("x-api-key", API_KEY)
            .contentType(ContentType.JSON);
            
    public static RequestSpecification getRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType("application/json")
            .header("x-api-key", API_KEY);

    public static ResponseSpecification getSingleSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();
            
    public static ResponseSpecification patchResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();
            
    public static ResponseSpecification postResponseSpecNegative = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .expectContentType(ContentType.JSON)
            .build();
            
    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectContentType(ContentType.JSON)
            .build();
            
    public static ResponseSpecification registerResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectContentType(ContentType.JSON)
            .build();
}
