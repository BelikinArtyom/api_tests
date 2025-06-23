package demoWebShopTests;

import com.codeborne.selenide.Configuration;
import helpers.TestBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;

public class LoginTests extends TestBase {

    String login = "qa@qa.guru",
            password = "qa@qa.guru1";

    @Test
    void loginWithUiTest() {

        step("Open login page", () ->
                open("/login"));

        step("Fill login form", () -> {
            $("#Email").setValue(login);
            $("#Password").setValue(password).pressEnter();
        });

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(login)));
    }

    @Test
    void loginWithApiTest() {

        step("Get cookie and set it to browser", () -> {
                String authCookieKey = "NOPCOMMERCE.AUTH";
                String authCookieValue = given()
                .headers("Content-type", "application/x-www-form-urlencoded")
                .formParam("email", login)
                .formParam("password", password)
                .when()
                .post("https://demowebshop.tricentis.com/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(authCookieKey);

                open("/favicon.ico");
                Cookie authnCookie = new Cookie(authCookieKey, authCookieValue);
                getWebDriver().manage().addCookie(authnCookie);
        });

        step("OPen main page", () -> {
            open(baseUrl);
                });

        step("Verify successful authorization", () -> {
                $(".account").shouldHave(text(login));
                });
    }
}


