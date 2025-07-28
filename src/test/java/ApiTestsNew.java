import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

import static io.restassured.http.ContentType.JSON;
import static java.lang.Math.log;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class ApiTestsNew {


    @Test
    void checkApiTotal () {
            given()
                 .log().uri()
                 .get("https://selenoid.autotests.cloud/status")
                 .then()
                 .log().body()
                    .statusCode(200)
                 .body("browsers.firefox", hasKey("124.0"));
    }


    @Test
    void loginTest () {

        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .post("https://reqres.in/api/login")


                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                        .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void anotherloginTest () {

        given()
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}")
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .post("https://reqres.in/api/login")


                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

}
