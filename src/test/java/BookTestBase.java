import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Configuration.baseUrl;

public class BookTestBase {

    @BeforeEach
    void allureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    public static void beforeAll() {

        baseUrl = "https://demoqa.com/";
        RestAssured.baseURI = "https://demoqa.com";

            Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
            System.getProperty("selenoid_login", "user1"),
            System.getProperty("selenoid_password", "1234"),
            System.getProperty("selenoid_host", "selenoid.autotests.cloud"));

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());


    }
}