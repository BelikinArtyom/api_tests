package helpers;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {

        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";

    Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
            System.getProperty("selenoid_login", "user1"),
            System.getProperty("selenoid_password", "1234"),
            System.getProperty("selenoid_host", "selenoid.autotests.cloud"));

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
}

}
