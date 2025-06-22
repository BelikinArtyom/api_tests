package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class HwPostLombokSpec {

    public static RequestSpecification postRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType("application/json")
            .header("x-api-key", "reqres-free-v1")
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON);
//            .baseUri("https://reqres.in")
//            .basePath("api/login");

    public static ResponseSpecification postResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

}
