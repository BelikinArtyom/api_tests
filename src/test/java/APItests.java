import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class APItests {

    // Получение API ключа из переменных окружения
    private static final String API_KEY = System.getProperty("api.key", "reqres-free-v1");

    @BeforeAll
    static void setup () {
        RestAssured.baseURI = System.getProperty("base.uri", "https://reqres.in");
        RestAssured.basePath = System.getProperty("base.path", "/api");
    }


    @Test
    void patchApiTestSuccessfulTest() {

        String patchData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        String currentMonth = String.format("%02d", LocalDateTime.now().getMonthValue());

        given().body(patchData)
                .contentType("application/json")
                .header("x-api-key", API_KEY)
                .when()

                .patch("/users/2")

                .then()
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", notNullValue())
                .body("updatedAt", containsString(currentYear))
                .body("updatedAt", containsString(currentMonth))
                .body("updatedAt", matchesPattern(".*T.*Z"));
    }

    @Test
    void patchApiTestSingleParameterTest() {

        String patchData = "{\"name\": \"\", \"job\": \"zion resident\"}";
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        String currentMonth = String.format("%02d", LocalDateTime.now().getMonthValue());

        given().body(patchData)
                .contentType("application/json")
                .header("x-api-key", API_KEY)
                .when()

                .patch("/users/2")

                .then()
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
        given()
                .header("x-api-key", API_KEY)
                .when()
                .get("/unknown/2")  // Убрана точка с запятой, добавлена точка
                .then()
                .statusCode(200)  // Добавьте проверку статуса
                .body("data.id", equalTo(2))
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", equalTo(2001))
                .body("data.color", equalTo("#C74375"))
                .body("data.pantone_value", equalTo("17-2031"));
    }

    @Test
    void postApiTestSuccessfulTest() {

        String patchData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        String currentMonth = String.format("%02d", LocalDateTime.now().getMonthValue());

        given().body(patchData)
                .contentType("application/json")
                .header("x-api-key", API_KEY)
                .when()

                .post("/users/2")

                .then()
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("createdAt", notNullValue())
                .body("createdAt", containsString(currentYear))
                .body("createdAt", containsString(currentMonth))
                .body("createdAt", matchesPattern(".*T.*Z"));
    }


    @Test
    void userNotFoundTest() {

        given()
                .header("x-api-key", API_KEY)
                .when()
                .get("/users/23")
                .then()
                .statusCode(404);

        get("/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    void failedRegistrationTest() {

        String patchData = "{\"email\": \"eve.holt@reqres.imn\", \"password\": \"knife\"}";

        given().body(patchData)
                .contentType("application/json")
                .header("x-api-key", API_KEY)
                .when()

                .post("/register")

                .then()
                .body("error", is("Note: Only defined users succeed registration"));

    }
}
