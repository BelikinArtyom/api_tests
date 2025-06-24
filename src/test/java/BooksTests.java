import helpers.BookTestBase;
import helpers.TestBase;
import io.restassured.response.Response;
import models.lombok.DemoQaLoginBodyModel;
import models.lombok.DemoQaLoginResponseModel;
import models.lombok.HwPatchLombokResponseModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static specs.DemoQaLoginSpec.bookLoginRequestSpec;
import static specs.HwPatchSpec.patchRequestSpec;


public class BooksTests extends BookTestBase {


    @Test
    void successfulLoginWithApiTest() {

         String login = "Mestray",
                password = "BEPcAxf5DsKk~@_",
                bookIsbn =  "9781449337711",
                userId = "0614ae60-ffda-4fe0-88a2-092b2da53b15";


//        String authData = "{\"userName\":\"" + login + "\",\"password\":\"" + password + "\"}";
        String bookData = "{\"userId\":\"" + userId + "\",\"collectionOfIsbns\":[{\"isbn\":\"" + bookIsbn + "\"}]}";

        DemoQaLoginBodyModel testData = DemoQaLoginBodyModel.createTestData();

        DemoQaLoginResponseModel response = step("Sent request", () -> {
            return given().spec(bookLoginRequestSpec)
                .body(testData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(DemoQaLoginResponseModel.class);
        });


        Response addBookResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .headers("Content-type", "application/json")
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().response();

        String isbn = "9781449337711";
        String deleteBookData = format("{\"userId\":\"%s\",\"isbn\":\"%s\"}",
                authResponse.path("userId") , isbn);

        Response deleteBookResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .headers("Content-type", "application/json; charset=utf-8")
                .body(deleteBookData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .log().status()
                .log().body()
                .statusCode(204)
                .extract().response();
    }
}
