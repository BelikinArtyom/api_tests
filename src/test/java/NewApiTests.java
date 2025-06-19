import io.restassured.RestAssured;
import models.lombok.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static specs.HwGetSingleSpec.*;
import static specs.HwPatchSpec.patchRequestSpec;
import static specs.HwPatchSpec.patchResponseSpec;
import static specs.HwPostLombokSpec.*;

public class NewApiTests {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }


    @Test
    void patchApiTestSuccessfulTest() {

        HwPatchLombokBodyModel authData = new HwPatchLombokBodyModel();

        String patchData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        String currentMonth = String.format("%02d", LocalDateTime.now().getMonthValue());

        HwPatchLombokResponseModel response = step("Sent request", () -> {
            return given().spec(patchRequestSpec)
                    .body(patchData)
                    .when()
                    .patch("/users/2")
                    .then()
                    .spec(patchResponseSpec)
                    .extract().as(HwPatchLombokResponseModel.class);
        });

        step("Check response", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("zion resident", response.getJob());
            assertNotNull(response.getUpdatedAt());
            assertTrue(response.getUpdatedAt().startsWith(currentYear + "-" + currentMonth));
            assertTrue(response.getUpdatedAt().endsWith("Z"));
            assertTrue(response.getUpdatedAt().contains("T"));
        });
    }

    @Test
    void getSingleObjectTest() {

        HwSingleGetResponseModel response = step("Sent request", () -> {
            return given().spec(getRequestSpec)
                    .when()
                    .get("/unknown/2")
                    .then()
                    .spec(getSingleSpec)
                    .extract().as(HwSingleGetResponseModel.class);
        });

        step("Check response data", () -> {
            assertNotNull(response.getData());
            assertEquals(2, response.getData().getId());
            assertEquals("fuchsia rose", response.getData().getName());
            assertEquals(2001, response.getData().getYear());
            assertEquals("#C74375", response.getData().getColor());
            assertEquals("17-2031", response.getData().getPantoneValue());
        });
    }

    @Test
    void postApiTestSuccessfulTest() {

        HwPostBodyLombokModel authData = new HwPostBodyLombokModel();

        String patchData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        String currentMonth = String.format("%02d", LocalDateTime.now().getMonthValue());

        HwPostBodyLombokModel response = step("Sent request", () -> {

            return given().spec(postRequestSpec)
                    .body(patchData)
                    .when()
                    .post("/api/users")
                    .then()
                    .spec(postResponseSpec)
                    .extract().as(HwPostBodyLombokModel.class);
        });

        step("Check response data", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
            assertNotNull(response.getCreatedAt());
            assertTrue(response.getCreatedAt().startsWith(currentYear + "-" + currentMonth));
        });
    }

    @Test
    void userNotFoundTest() {

        HwSingleGetResponseModel response = step("Sent request", () -> {
            return given().spec(getRequestSpec)
                    .when()
                    .get("/users/23/")
                    .then()
                    .spec(postResponseSpecNegative)
                    .extract().as(HwSingleGetResponseModel.class);
        });

        step("Check that response body is empty (no user data)", () -> {
            assertNull(response.getData(), "Expected no user data for non-existing user");
            assertNull(response.getSupport(), "Expected no support info for non-existing user");
        });
    }


    @Test
    void patchApiTestSingleParameterTest() {


        HwPatchLombokBodyModel authData = new HwPatchLombokBodyModel();

        String patchData = "{\"name\": \"\", \"job\": \"zion resident\"}";
        String currentYear = String.valueOf(LocalDateTime.now().getYear());
        String currentMonth = String.format("%02d", LocalDateTime.now().getMonthValue());

        HwPatchLombokResponseModel response = step("Sent request", () -> {
            return given().spec(patchRequestSpec)
                    .body(patchData)
                    .when()
                    .patch("/users/2")
                    .then()
                    .spec(patchResponseSpec)
                    .extract().as(HwPatchLombokResponseModel.class);
        });

        step("Check response", () -> {
            assertEquals("", response.getName());
            assertEquals("zion resident", response.getJob());
            assertNotNull(response.getUpdatedAt());
            assertTrue(response.getUpdatedAt().startsWith(currentYear + "-" + currentMonth));
            assertTrue(response.getUpdatedAt().endsWith("Z"));
            assertTrue(response.getUpdatedAt().contains("T"));
        });
    }

}