package helpers;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.baseUrl;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {

        String login = "qa@qa.guru",
                password = "qa@qa.guru1";
                baseUrl = "https://demowebshop.tricentis.com/";


//            Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
//            System.getProperty("selenoid_login", "user1"),
//            System.getProperty("selenoid_password", "1234"),
//            System.getProperty("selenoid_host", "selenoid.autotests.cloud"));

            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
}

}
