package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class HwPatchSpec {

    public static RequestSpecification patchRequestSpec = with()
            .contentType("application/json")  //
            .header("x-api-key", "reqres-free-v1")
            .log().uri();


    public static ResponseSpecification patchResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

}
