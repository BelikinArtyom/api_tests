package demoWebShopTests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static java.lang.Thread.sleep;

public class LoginTests {

    String login = "qa@qa.guru",
           password = "qa@qa.guru1";


    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demowebshop.tricentis.com/";

    }

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

        step("Get cookie by api and set it to browser", () ->

                String authorizationCookie = given()
                .headers()
                .body(login, password)
                .when()
                .post("https://demowebshop.tricentis.com/login")
                         .then()
                .statusCode()
                .extract()
                .cookie()

    });

//        step("Open login page", () ->
//                open("/login"));
//
//        step("Fill login form", () -> {
//            $("#Email").setValue(login);
//            $("#Password").setValue(password).pressEnter();
//        });

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(login)));
    }



    }

