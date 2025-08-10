import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    // API ключи из переменных окружения
    protected static final String API_KEY = System.getProperty("api.key", "reqres-free-v1");
    protected static final String BASE_URI = System.getProperty("base.uri", "https://reqres.in");
    protected static final String BASE_PATH = System.getProperty("base.path", "/api");

    @BeforeAll
    public static void beforeAll() {

        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;

//    Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
//            System.getProperty("selenoid_login", "user1"),
//            System.getProperty("selenoid_password", "1234"),
//            System.getProperty("selenoid_host", "selenoid.autotests.cloud"));

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
}

}
