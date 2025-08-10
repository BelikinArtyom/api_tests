
import io.qameta.allure.*;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.ApiSpecifications.*;


public class ReqresApiTests extends ApiTestBase {

    @Feature("Api tests")
    @Story("reqres")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("Auto run")
    @DisplayName("PATCH: Обновление имени и должности пользователя")
    @Test
    void shouldUpdateUserJobAndNameSuccessfully() {

        RequestBodyModel testData = RequestBodyModel.createPatchData();
        String currentYear = RequestBodyModel.getCurrentYear();
        String currentMonth = RequestBodyModel.getCurrentMonth();

        PatchResponseModel response = step("Подготавливаем тестовые данные для обновления пользователя", () -> {
            return given().spec(patchRequestSpec)
                    .body(testData)
                    .when()
                    .patch("/users/2")
                    .then()
                    .spec(patchResponseSpec)
                    .extract().as(PatchResponseModel.class);
        });

        step("Проверяем, что имя пользователя обновлено корректно", () -> {
            assertEquals("morpheus", response.getName());
        });

        step("Проверяем, что должность пользователя обновлена корректно", () -> {
            assertEquals("zion resident", response.getJob());
        });

        step("Проверяем, что время обновления установлено", () -> {
            assertNotNull(response.getUpdatedAt());
        });

        step("Проверяем формат времени обновления", () -> {
            assertTrue(response.getUpdatedAt().startsWith(currentYear + "-" + currentMonth));
            assertTrue(response.getUpdatedAt().endsWith("Z"));
            assertTrue(response.getUpdatedAt().contains("T"));
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("Auto run")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET: Получение объекта цвета с детальной информацией")
    @Test
    void shouldRetrieveSingleColorObjectSuccessfully() {

        SingleGetResponseModel response = step("Отправляем запрос на получение объекта цвета", () -> {
            return given().spec(getRequestSpec)
                    .when()
                    .get("/unknown/2")
                    .then()
                    .spec(getSingleSpec)
                    .extract().as(SingleGetResponseModel.class);
        });

        step("Проверяем, что данные объекта получены", () -> {
            assertNotNull(response.getData());
        });

        step("Проверяем корректность ID объекта", () -> {
            assertEquals(2, response.getData().getId());
        });

        step("Проверяем корректность названия цвета", () -> {
            assertEquals("fuchsia rose", response.getData().getName());
        });

        step("Проверяем корректность года", () -> {
            assertEquals(2001, response.getData().getYear());
        });

        step("Проверяем корректность hex-кода цвета", () -> {
            assertEquals("#C74375", response.getData().getColor());
        });

        step("Проверяем корректность Pantone значения", () -> {
            assertEquals("17-2031", response.getData().getPantoneValue());
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("Auto run")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST: Создание нового пользователя")
    @Test
    void shouldCreateNewUserSuccessfully() {

        RequestBodyModel testData = RequestBodyModel.createPostData();
        String currentYear = RequestBodyModel.getCurrentYear();
        String currentMonth = RequestBodyModel.getCurrentMonth();

        RequestBodyModel response = step("Отправляем запрос на создание нового пользователя", () -> {
            return given().spec(postRequestSpec)
                    .body(testData)
                    .when()
                    .post("/api/users")
                    .then()
                    .spec(postResponseSpec)
                    .extract().as(RequestBodyModel.class);
        });

        step("Проверяем, что имя пользователя создано корректно", () -> {
            assertEquals("morpheus", response.getName());
        });

        step("Проверяем, что должность пользователя создана корректно", () -> {
            assertEquals("leader", response.getJob());
        });

        step("Проверяем, что время создания установлено", () -> {
            assertNotNull(response.getCreatedAt());
        });

        step("Проверяем формат времени создания", () -> {
            assertTrue(response.getCreatedAt().startsWith(currentYear + "-" + currentMonth));
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("Auto run")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET: Обработка несуществующего пользователя")
    @Test
    void shouldReturnEmptyResponseForNonExistingUser() {

        SingleGetResponseModel response = step("Отправляем запрос на получение несуществующего пользователя", () -> {
            return given().spec(getRequestSpec)
                    .when()
                    .get("/users/23/")
                    .then()
                    .spec(postResponseSpecNegative)
                    .extract().as(SingleGetResponseModel.class);
        });

        step("Проверяем, что данные пользователя отсутствуют", () -> {
            assertNull(response.getData(), "Expected no user data for non-existing user");
        });

        step("Проверяем, что информация поддержки отсутствует", () -> {
            assertNull(response.getSupport(), "Expected no support info for non-existing user");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("Auto run")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("PATCH: Частичное обновление пользователя (только должность)")
    @Test
    void shouldUpdateUserJobOnlySuccessfully() {

        RequestBodyModel testData = RequestBodyModel.createPatchSingle();
        String currentYear = RequestBodyModel.getCurrentYear();
        String currentMonth = RequestBodyModel.getCurrentMonth();

        PatchResponseModel response = step("Отправляем запрос на частичное обновление пользователя", () -> {
            return given().spec(patchRequestSpec)
                    .body(testData)
                    .when()
                    .patch("/users/2")
                    .then()
                    .spec(patchResponseSpec)
                    .extract().as(PatchResponseModel.class);
        });

        step("Проверяем, что имя пользователя осталось пустым", () -> {
            assertEquals("", response.getName());
        });

        step("Проверяем, что должность пользователя обновлена", () -> {
            assertEquals("zion resident", response.getJob());
        });

        step("Проверяем, что время обновления установлено", () -> {
            assertNotNull(response.getUpdatedAt());
        });

        step("Проверяем формат времени обновления", () -> {
            assertTrue(response.getUpdatedAt().startsWith(currentYear + "-" + currentMonth));
            assertTrue(response.getUpdatedAt().endsWith("Z"));
            assertTrue(response.getUpdatedAt().contains("T"));
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("Auto run")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST: Регистрация нового пользователя")
    @Test
    void shouldRegisterNewUserSuccessfully() {

        RegisterRequestModel testData = RegisterRequestModel.createSuccessfulRegisterData();

        RegisterResponseModel response = step("Отправляем запрос на регистрацию нового пользователя", () -> {
            return given().spec(registerRequestSpec)
                    .body(testData)
                    .when()
                    .post("/api/register")
                    .then()
                    .spec(registerResponseSpec)
                    .statusCode(201)
                    .extract().as(RegisterResponseModel.class);
        });

        step("Логируем ответ для отладки", () -> {
            System.out.println("Register Response: " + response);
        });

        step("Проверяем, что email в ответе соответствует отправленному", () -> {
            assertEquals(testData.getEmail(), response.getEmail(), "Email should match request data");
        });

        step("Проверяем, что пароль в ответе соответствует отправленному", () -> {
            assertEquals(testData.getPassword(), response.getPassword(), "Password should match request data");
        });

        step("Проверяем, что ID пользователя сгенерирован", () -> {
            assertFalse(response.getId().isEmpty(), "User ID should not be empty");
        });

        step("Проверяем, что время создания установлено", () -> {
            assertFalse(response.getCreatedAt().isEmpty(), "CreatedAt should not be empty");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("Auto run")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST: Успешный вход пользователя в систему")
    @Test
    void shouldLoginUserSuccessfully() {

        LoginRequestModel testData = LoginRequestModel.createSuccessfulLoginData();

        LoginResponseModel response = step("Отправляем запрос на вход пользователя", () -> {
            return given().spec(loginRequestSpec)
                    .body(testData)
                    .when()
                    .post("/api/login")
                    .then()
                    .spec(loginResponseSpec)
                    .extract().as(LoginResponseModel.class);
        });

        step("Проверяем, что ID пользователя получен", () -> {
            assertNotNull(response.getId(), "ID should not be null");
            assertFalse(response.getId().isEmpty(), "ID should not be empty");
        });

        step("Проверяем, что email пользователя получен", () -> {
            assertNotNull(response.getEmail(), "Email should not be null");
            assertFalse(response.getEmail().isEmpty(), "Email should not be empty");
        });

        step("Проверяем, что пароль пользователя получен", () -> {
            assertNotNull(response.getPassword(), "Password should not be null");
            assertFalse(response.getPassword().isEmpty(), "Password should not be empty");
        });

        step("Проверяем, что username пользователя получен", () -> {
            assertNotNull(response.getUsername(), "Username should not be null");
            assertFalse(response.getUsername().isEmpty(), "Username should not be empty");
        });

        step("Проверяем, что время создания установлено", () -> {
            assertNotNull(response.getCreatedAt(), "CreatedAt should not be null");
            assertFalse(response.getCreatedAt().isEmpty(), "CreatedAt should not be empty");
        });

        step("Проверяем, что ошибка отсутствует", () -> {
            assertNull(response.getError(), "Error should be null for successful login");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("Auto run")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET: Получение списка пользователей с пагинацией")
    @Test
    void shouldRetrieveUserListWithPaginationSuccessfully() {

        int pageNumber = 2;

        UserListResponseModel response = step("Отправляем запрос на получение списка пользователей с пагинацией", () -> {
            return given().spec(getRequestSpec)
                    .queryParam("page", pageNumber)
                    .when()
                    .get("/users")
                    .then()
                    .spec(getSingleSpec)
                    .extract().as(UserListResponseModel.class);
        });

        step("Проверяем корректность номера страницы", () -> {
            assertEquals(pageNumber, response.getPage(), "Page should be 2");
        });

        step("Проверяем количество элементов на странице", () -> {
            assertEquals(6, response.getPer_page(), "Per page should be 6");
        });

        step("Проверяем общее количество пользователей", () -> {
            assertTrue(response.getTotal() > 0, "Total should be greater than 0");
        });

        step("Проверяем общее количество страниц", () -> {
            assertTrue(response.getTotal_pages() > 0, "Total pages should be greater than 0");
        });

        step("Проверяем наличие данных пользователей", () -> {
            assertNotNull(response.getData(), "Data should not be null");
            assertFalse(response.getData().isEmpty(), "Data should not be empty");
        });

        step("Проверяем наличие информации поддержки", () -> {
            assertNotNull(response.getSupport(), "Support should not be null");
        });
        
        step("Проверяем данные первого пользователя на странице", () -> {
            UserModel firstUser = response.getData().get(0);
            assertTrue(firstUser.getId() > 0, "User ID should be positive");
            assertNotNull(firstUser.getEmail(), "User email should not be null");
            assertNotNull(firstUser.getFirst_name(), "User first name should not be null");
            assertNotNull(firstUser.getLast_name(), "User last name should not be null");
            assertNotNull(firstUser.getAvatar(), "User avatar should not be null");
        });
    }
}