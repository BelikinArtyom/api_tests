import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class APItests {


    @Test
    void patchApiTestSuccessful() {

        String patchData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        String currentMonth = String.format("%02d", LocalDateTime.now().getMonthValue());

        given().body(patchData)
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()

                .patch("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()

                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", notNullValue())
                .body("updatedAt", containsString(currentYear))
                .body("updatedAt", containsString(currentMonth))
                .body("updatedAt", matchesPattern(".*T.*Z"));
    }

    @Test
    void patchApiTestSingleParameter() {

        String patchData = "{\"name\": \"\", \"job\": \"zion resident\"}";
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        String currentMonth = String.format("%02d", LocalDateTime.now().getMonthValue());

        given().body(patchData)
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()

                .patch("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()

                .statusCode(200)
                .body("name", is(""))
                .body("job", is("zion resident"))
                .body("updatedAt", notNullValue())
                .body("updatedAt", containsString(currentYear))
                .body("updatedAt", containsString(currentMonth))
                .body("updatedAt", matchesPattern(".*T.*Z"));
    }

    @Test
    void getSingleObjectTest() {
        get("https://reqres.in/api/unknown/2")
                .then()
                .body("data.id", equalTo(2))
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", equalTo(2001))
                .body("data.color", equalTo("#C74375"))
                .body("data.pantone_value", equalTo("17-2031"));
    }

    @Test
    void postApiTestSuccessful() {

        String patchData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        String currentMonth = String.format("%02d", LocalDateTime.now().getMonthValue());

        given().body(patchData)
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()

                .post("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()

                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("createdAt", notNullValue())
                .body("createdAt", containsString(currentYear))
                .body("createdAt", containsString(currentMonth))
                .body("createdAt", matchesPattern(".*T.*Z"));
    }


    @Test
    void userNotFoundTest() {
        get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    void failedRegistrationTest() {

        String patchData = "{\"email\": \"eve.holt@reqres.imn\", \"password\": \"knife\"}";
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        String currentMonth = String.format("%02d", LocalDateTime.now().getMonthValue());

        given().body(patchData)
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .when()

                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .body("error", is("Note: Only defined users succeed registration"));

    }
}
