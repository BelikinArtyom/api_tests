
import io.qameta.allure.*;
import models.lombok.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.HwSpec.*;


public class NewApiTests extends TestBase {

    @Feature("Api tests")
    @Story("reqres")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("WorkWork")
    @Test
    void patchApiTestSuccessfulTest() {

        HwBodyLombokModel testData = HwBodyLombokModel.createPatchData();
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

        HwBodyLombokModel testData = HwBodyLombokModel.createPostData();
        String currentYear = HwBodyLombokModel.getCurrentYear();
        String currentMonth = HwBodyLombokModel.getCurrentMonth();

        HwBodyLombokModel response = step("Sent request", () -> {

            return given().spec(postRequestSpec)
                    .body(testData)
                    .when()
                    .post("/api/users")
                    .then()
                    .spec(postResponseSpec)
                    .extract().as(HwBodyLombokModel.class);
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


        HwBodyLombokModel testData = HwBodyLombokModel.createPatchSingle();
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



    @Feature("Api tests")
    @Story("reqres")
    @Tag("WorkWork")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void successfulRegisterTest() {

        RegisterRequestModel testData = RegisterRequestModel.createSuccessfulRegisterData();

        RegisterResponseModel response = step("Sent register request", () -> {
            return given().spec(registerRequestSpec)
                    .body(testData)
                    .when()
                    .post("/api/register")
                    .then()
                    .spec(registerResponseSpec)
                    .statusCode(201)
                    .extract().as(RegisterResponseModel.class);
        });

        step("Check register response", () -> {

            assertNotNull(response.getId(), "User ID should not be null");
            assertFalse(response.getId().isEmpty(), "User ID should not be empty");
            assertNotNull(response.getEmail(), "Email should not be null");
            assertFalse(response.getEmail().isEmpty(), "Email should not be empty");
            assertNotNull(response.getCreatedAt(), "CreatedAt should not be null");
            assertFalse(response.getCreatedAt().isEmpty(), "CreatedAt should not be empty");
            assertNull(response.getError(), "Error should be null for successful registration");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("WorkWork")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void successfulLoginTest() {

        LoginRequestModel testData = LoginRequestModel.createSuccessfulLoginData();

        LoginResponseModel response = step("Sent login request", () -> {
            return given().spec(loginRequestSpec)
                    .body(testData)
                    .when()
                    .post("/api/login")
                    .then()
                    .spec(loginResponseSpec)
                    .extract().as(LoginResponseModel.class);
        });

        step("Check login response", () -> {
            assertNotNull(response.getId(), "ID should not be null");
            assertFalse(response.getId().isEmpty(), "ID should not be empty");
            assertNotNull(response.getEmail(), "Email should not be null");
            assertFalse(response.getEmail().isEmpty(), "Email should not be empty");
            assertNotNull(response.getPassword(), "Password should not be null");
            assertFalse(response.getPassword().isEmpty(), "Password should not be empty");
            assertNotNull(response.getCreatedAt(), "CreatedAt should not be null");
            assertFalse(response.getCreatedAt().isEmpty(), "CreatedAt should not be empty");
            assertNull(response.getError(), "Error should be null for successful login");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("WorkWork")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void getUserListWithPaginationTest() {

        int pageNumber = 2;

        UserListResponseModel response = step("Sent request for user list", () -> {
            return given().spec(getRequestSpec)
                    .queryParam("page", pageNumber)
                    .when()
                    .get("/users")
                    .then()
                    .spec(getSingleSpec)
                    .extract().as(UserListResponseModel.class);
        });

        step("Check pagination response", () -> {
            assertEquals(pageNumber, response.getPage(), "Page should be 2");
            assertEquals(6, response.getPer_page(), "Per page should be 6");
            assertTrue(response.getTotal() > 0, "Total should be greater than 0");
            assertTrue(response.getTotal_pages() > 0, "Total pages should be greater than 0");
            assertNotNull(response.getData(), "Data should not be null");
            assertFalse(response.getData().isEmpty(), "Data should not be empty");
            assertNotNull(response.getSupport(), "Support should not be null");
            
            // Проверяем первого пользователя на странице
            UserModel firstUser = response.getData().get(0);
            assertTrue(firstUser.getId() > 0, "User ID should be positive");
            assertNotNull(firstUser.getEmail(), "User email should not be null");
            assertNotNull(firstUser.getFirst_name(), "User first name should not be null");
            assertNotNull(firstUser.getLast_name(), "User last name should not be null");
            assertNotNull(firstUser.getAvatar(), "User avatar should not be null");
        });
    }
}