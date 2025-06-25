import helpers.AuthContext;
import helpers.WithLoginExtension;
import io.qameta.allure.*;
import io.restassured.response.Response;
import models.lombok.AddDeleteBodyModel;
import models.lombok.DemoQaBodyModel;
import models.lombok.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQaLoginSpec.*;

@ExtendWith(WithLoginExtension.class)
public class BooksTests extends BookTestBase {

    @Feature("Книги")
    @Story("Корзина")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление и удаление товара из корзины")
    @Tag("WorkWork")
    @WithLogin(username = "Mestray", password = "BEPcAxf5DsKk~@_")
    @Test
    void addAndDeleteBookTest() {

        AddDeleteBodyModel addBook = AddDeleteBodyModel.addBook();
        DemoQaBodyModel testData = DemoQaBodyModel.createTestData();
        AddDeleteBodyModel deleteBook = AddDeleteBodyModel.DeleteBookData();
        AddDeleteBodyModel checkBooks = AddDeleteBodyModel.checkBooks();


        Response authResponse = step("API авторизация", () -> {
            return given().spec(bookLoginRequestSpec)
                    .body(testData)
                    .when()
                    .post("/Account/v1/Login")
                    .then()
                    .spec(bookLoginResponseSpec)
                    .extract().response();
        });

        Response addBookResponse = step("Добавление книги в корзину", () -> {
            return given()
                    .spec(bookAddRequestSpec)
                    .header("Authorization", "Bearer " + authResponse.path("token"))
                    .body(addBook)
                    .when()
                    .post("/BookStore/v1/Books")
                    .then()
                    .spec(bookAddResponseSpec)
                    .extract().response();
        });

        Response deleteBookResponse = step("Удаление книги из корзины", () -> {
          return given()
                .spec(bookDeleteRequestSpec)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .headers("Content-type", "application/json; charset=utf-8")
                .body(deleteBook)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(bookDeleteResponseSpec)
                .extract().response();
        });

        step ("Проверяем количество книг в профиле", () -> {
            Response response = given()
                    .spec(bookCheckRequestSpec)
                    .header("Authorization", "Bearer " + authResponse.path("token"))
                    .when()
                    .get("/Account/v1/User/" + checkBooks.getUserId())
                    .then()
                    .spec(bookCheckResponseSpec)
                    .extract().response();

            AddDeleteBodyModel.validateUserProfile(response);
        });
    }
}
