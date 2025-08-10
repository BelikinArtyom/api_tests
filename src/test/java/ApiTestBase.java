import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ApiTestBase {
    
    protected static final String BASE_URI = System.getProperty("base.uri", "https://reqres.in");
    protected static final String BASE_PATH = System.getProperty("base.path", "/api");
    
    @BeforeEach
    void allureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    public static void beforeAll() {

        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;

    Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
            System.getProperty("selenoid_login", "user1"),
            System.getProperty("selenoid_password", "1234"),
            System.getProperty("selenoid_host", "selenoid.autotests.cloud"));

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
}
    @AfterEach
    void attach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.getVideoUrl();
        Attach.addVideo();
    }
}
