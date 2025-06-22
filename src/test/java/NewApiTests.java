
import helpers.TestBase;
import io.qameta.allure.*;
import models.lombok.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.HwGetSingleSpec.*;
import static specs.HwPatchSpec.patchRequestSpec;
import static specs.HwPatchSpec.patchResponseSpec;
import static specs.HwPostLombokSpec.*;

public class NewApiTests extends TestBase {

    @Feature("Api tests")
    @Story("reqres")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("WorkWork")
    @Test
    void patchApiTestSuccessfulTest() {

        HwPatchLombokBodyModel testData = HwBodyLombokModel.createPatchData();
        String currentYear = HwBodyLombokModel.getCurrentYear();
        String currentMonth = HwBodyLombokModel.getCurrentMonth();

        HwPatchLombokResponseModel response = step("Sent request", () -> {
            return given().spec(patchRequestSpec)
                    .body(testData)
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

    @Feature("Api tests")
    @Story("reqres")
    @Tag("WorkWork")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
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

    @Feature("Api tests")
    @Story("reqres")
    @Tag("WorkWork")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void postApiTestSuccessfulTest() {

        HwPostBodyLombokModel testData = HwBodyLombokModel.createPostData();
        String currentYear = HwBodyLombokModel.getCurrentYear();
        String currentMonth = HwBodyLombokModel.getCurrentMonth();

        HwPostBodyLombokModel response = step("Sent request", () -> {

            return given().spec(postRequestSpec)
                    .body(testData)
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

    @Feature("Api tests")
    @Story("reqres")
    @Tag("WorkWork")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
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

    @Feature("Api tests")
    @Story("reqres")
    @Tag("WorkWork")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void patchApiTestSingleParameterTest() {


        HwPatchSingleLombokBodyModel testData = HwBodyLombokModel.createTestData();
        String currentYear = HwBodyLombokModel.getCurrentYear();
        String currentMonth = HwBodyLombokModel.getCurrentMonth();

        HwPatchLombokResponseModel response = step("Sent request", () -> {
            return given().spec(patchRequestSpec)
                    .body(testData)
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