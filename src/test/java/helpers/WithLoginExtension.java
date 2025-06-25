package helpers;

import models.lombok.DemoQaBodyModel;
import models.lombok.WithLogin;
import org.junit.jupiter.api.extension.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specs.DemoQaLoginSpec.bookLoginRequestSpec;
import static specs.DemoQaLoginSpec.bookLoginResponseSpec;

public class WithLoginExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        WithLogin annotation = context.getRequiredTestMethod().getAnnotation(WithLogin.class);



        if (annotation != null) {
            String username = annotation.username();
            String password = annotation.password();

            // создаём модель и подставляем логин/пароль
            DemoQaBodyModel credentials = DemoQaBodyModel.createTestData();
            credentials.setUserName(username);
            credentials.setPassword(password);

            // Выполняем API логин
            Response loginResponse = given()
                    .spec(bookLoginRequestSpec)
                    .body(credentials)
                    .when()
                    .post("/Account/v1/Login")
                    .then()
                    .spec(bookLoginResponseSpec)
                    .extract().response();

            String token = loginResponse.path("token");
            String userId = loginResponse.path("userId");

            AuthContext.setToken(token);
            AuthContext.setUserId(userId);
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        AuthContext.clear();
    }
}