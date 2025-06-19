package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class HwGetSingleSpec {

    public static RequestSpecification getRequestSpec = with()
            .contentType("application/json")
            .header("x-api-key", "reqres-free-v1")
            .log().uri()
            .log().body();

    public static ResponseSpecification getSingleSpec = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .log(STATUS)
                    .log(BODY)
                    .build();

    public static ResponseSpecification postResponseSpecNegative = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(STATUS)
            .log(BODY)
            .build();
}
