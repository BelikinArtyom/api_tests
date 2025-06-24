import helpers.BookTestBase;
import io.restassured.response.Response;
import models.lombok.AddDeleteBodyModel;
import models.lombok.DemoQaBodyModel;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static specs.DemoQaLoginSpec.*;

public class BooksTests extends BookTestBase {

    @Test
    void addAndDeleteBookTest() {

        AddDeleteBodyModel addBook = AddDeleteBodyModel.addBook();
        DemoQaBodyModel testData = DemoQaBodyModel.createTestData();
        AddDeleteBodyModel deleteBook = AddDeleteBodyModel.DeleteBookData();

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
                    .header("Content-Type", "application/json")
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
                .spec(bookAddResponseSpec)
                .extract().response();
        });
    }
}
