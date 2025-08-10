
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
    @Tag("AutoRun")
    @DisplayName("PATCH: Обновление имени и должности пользователя")
    @Test
    void updateUserJobAndNameTest() {

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
            assertEquals("morpheus", response.getName(), "Имя пользователя должно быть 'morpheus'");
        });

        step("Проверяем, что должность пользователя обновлена корректно", () -> {
            assertEquals("zion resident", response.getJob(), "Должность пользователя должна быть 'zion resident'");
        });

        step("Проверяем, что время обновления установлено", () -> {
            assertNotNull(response.getUpdatedAt(), "Время обновления не должно быть null");
        });

        step("Проверяем формат времени обновления", () -> {
            assertTrue(response.getUpdatedAt().startsWith(currentYear + "-" + currentMonth), "Время обновления должно начинаться с текущего года и месяца");
            assertTrue(response.getUpdatedAt().endsWith("Z"), "Время обновления должно заканчиваться на 'Z'");
            assertTrue(response.getUpdatedAt().contains("T"), "Время обновления должно содержать 'T'");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("AutoRun")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET: Получение объекта цвета с детальной информацией")
    @Test
    void retrieveSingleColorObjectTest() {

        SingleGetResponseModel response = step("Отправляем запрос на получение объекта цвета", () -> {
            return given().spec(getRequestSpec)
                    .when()
                    .get("/unknown/2")
                    .then()
                    .spec(getSingleSpec)
                    .extract().as(SingleGetResponseModel.class);
        });

        step("Проверяем, что данные объекта получены", () -> {
            assertNotNull(response.getData(), "Данные объекта не должны быть null");
        });

        step("Проверяем корректность ID объекта", () -> {
            assertEquals(2, response.getData().getId(), "ID объекта должен быть равен 2");
        });

        step("Проверяем корректность названия цвета", () -> {
            assertEquals("fuchsia rose", response.getData().getName(), "Название цвета должно быть 'fuchsia rose'");
        });

        step("Проверяем корректность года", () -> {
            assertEquals(2001, response.getData().getYear(), "Год должен быть равен 2001");
        });

        step("Проверяем корректность hex-кода цвета", () -> {
            assertEquals("#C74375", response.getData().getColor(), "Hex-код цвета должен быть '#C74375'");
        });

        step("Проверяем корректность Pantone значения", () -> {
            assertEquals("17-2031", response.getData().getPantoneValue(), "Pantone значение должно быть '17-2031'");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("AutoRun")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST: Создание нового пользователя")
    @Test
    void createNewUserTest() {

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
            assertEquals("morpheus", response.getName(), "Имя пользователя должно быть 'morpheus'");
        });

        step("Проверяем, что должность пользователя создана корректно", () -> {
            assertEquals("leader", response.getJob(), "Должность пользователя должна быть 'leader'");
        });

        step("Проверяем, что время создания установлено", () -> {
            assertNotNull(response.getCreatedAt(), "Время создания не должно быть null");
        });

        step("Проверяем формат времени создания", () -> {
            assertTrue(response.getCreatedAt().startsWith(currentYear + "-" + currentMonth), "Время создания должно начинаться с текущего года и месяца");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("AutoRun")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET: Обработка несуществующего пользователя")
    @Test
    void returnEmptyResponseForNonExistingUserTest() {

        SingleGetResponseModel response = step("Отправляем запрос на получение несуществующего пользователя", () -> {
            return given().spec(getRequestSpec)
                    .when()
                    .get("/users/23/")
                    .then()
                    .spec(postResponseSpecNegative)
                    .extract().as(SingleGetResponseModel.class);
        });

        step("Проверяем, что данные пользователя отсутствуют", () -> {
            assertNull(response.getData(), "Данные пользователя должны отсутствовать для несуществующего пользователя");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("AutoRun")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("PATCH: Частичное обновление пользователя (только должность)")
    @Test
    void updateUserJobOnlyTest() {

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
            assertEquals("", response.getName(), "Имя пользователя должно остаться пустым");
        });

        step("Проверяем, что должность пользователя обновлена", () -> {
            assertEquals("zion resident", response.getJob(), "Должность пользователя должна быть обновлена на 'zion resident'");
        });

        step("Проверяем, что время обновления установлено", () -> {
            assertNotNull(response.getUpdatedAt(), "Время обновления не должно быть null");
        });

        step("Проверяем формат времени обновления", () -> {
            assertTrue(response.getUpdatedAt().startsWith(currentYear + "-" + currentMonth), "Время обновления должно начинаться с текущего года и месяца");
            assertTrue(response.getUpdatedAt().endsWith("Z"), "Время обновления должно заканчиваться на 'Z'");
            assertTrue(response.getUpdatedAt().contains("T"), "Время обновления должно содержать 'T'");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("AutoRun")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST: Регистрация нового пользователя")
    @Test
    void registerNewUserTest() {

        RegisterRequestModel testData = RegisterRequestModel.createSuccessfulRegisterData();

        RegisterResponseModel response = step("Отправляем запрос на регистрацию нового пользователя", () -> {
            return given().spec(registerRequestSpec)
                    .body(testData)
                    .when()
                    .post("/api/register")
                    .then()
                    .spec(registerResponseSpec)
                    .statusCode(201)
                    .log().body()
                    .extract().as(RegisterResponseModel.class);
        });

        step("Проверяем, что у пользователя есть ID", () -> {
            assertNotNull(response.getId(), "ID пользователя не должен быть null");
            assertFalse(response.getId().isEmpty(), "ID пользователя не должен быть пустым");
        });

        step("Проверяем, что у пользователя есть username", () -> {
            assertNotNull(response.getUsername(), "Username не должен быть null");
            assertFalse(response.getUsername().isEmpty(), "Username не должен быть пустым");
        });

        step("Проверяем, что у пользователя есть email", () -> {
            assertNotNull(response.getEmail(), "Email не должен быть null");
            assertFalse(response.getEmail().isEmpty(), "Email не должен быть пустым");
        });

        step("Проверяем, что у пользователя есть password", () -> {
            assertNotNull(response.getPassword(), "Password не должен быть null");
            assertFalse(response.getPassword().isEmpty(), "Password не должен быть пустым");
        });

        step("Проверяем, что время создания установлено", () -> {
            assertNotNull(response.getCreatedAt(), "Время создания не должно быть null");
            assertFalse(response.getCreatedAt().isEmpty(), "Время создания не должно быть пустым");
        });
    }

    private LoginRequestModel createLoginDataFromRegisterData(RegisterRequestModel registerData) {
        return new LoginRequestModel(
            registerData.getUsername(),
            registerData.getEmail(),
            registerData.getPassword()
        );
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("AutoRun")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST: Успешный вход пользователя в систему")
    @Test
    void loginUserTest() {

        RegisterRequestModel registerData = RegisterRequestModel.createSuccessfulRegisterData();

        RegisterResponseModel registerResponse = step("Регистрируем нового пользователя", () -> {
            return given().spec(registerRequestSpec)
                    .body(registerData)
                    .when()
                    .post("/api/register")
                    .then()
                    .spec(registerResponseSpec)
                    .statusCode(201)
                    .extract().as(RegisterResponseModel.class);
        });

        LoginRequestModel loginData = createLoginDataFromRegisterData(registerData);

        LoginResponseModel loginResponse = step("Отправляем запрос на вход пользователя", () -> {
            return given().spec(loginRequestSpec)
                    .body(loginData)
                    .when()
                    .post("/api/login")
                    .then()
                    .statusCode(201)
                    .spec(loginResponseSpec)
                    .log().body()
                    .extract().as(LoginResponseModel.class);
        });

        step("Проверяем, что ID пользователя получен", () -> {
            assertNotNull(loginResponse.getId(), "ID пользователя не должен быть null");
            assertFalse(loginResponse.getId().isEmpty(), "ID пользователя не должен быть пустым");
        });

        step("Проверяем, что username пользователя получен", () -> {
            assertNotNull(loginResponse.getUsername(), "Username не должен быть null");
            assertFalse(loginResponse.getUsername().isEmpty(), "Username не должен быть пустым");
        });

        step("Проверяем, что email пользователя получен", () -> {
            assertNotNull(loginResponse.getEmail(), "Email не должен быть null");
            assertFalse(loginResponse.getEmail().isEmpty(), "Email не должен быть пустым");
        });

        step("Проверяем, что password пользователя получен", () -> {
            assertNotNull(loginResponse.getPassword(), "Password не должен быть null");
            assertFalse(loginResponse.getPassword().isEmpty(), "Password не должен быть пустым");
        });

        step("Проверяем, что время создания установлено", () -> {
            assertNotNull(loginResponse.getCreatedAt(), "Время создания не должно быть null");
            assertFalse(loginResponse.getCreatedAt().isEmpty(), "Время создания не должно быть пустым");
        });

        step("Проверяем, что данные для логина соответствуют данным регистрации", () -> {
            assertEquals(registerData.getUsername(), loginData.getUsername(), "Username должен соответствовать данным регистрации");
            assertEquals(registerData.getEmail(), loginData.getEmail(), "Email должен соответствовать данным регистрации");
            assertEquals(registerData.getPassword(), loginData.getPassword(), "Password должен соответствовать данным регистрации");
        });

        step("Проверяем, что данные ответа соответствуют переданным значениям", () -> {
            assertEquals(loginData.getUsername(), loginResponse.getUsername(), "Username в ответе должен соответствовать запросу");
            assertEquals(loginData.getEmail(), loginResponse.getEmail(), "Email в ответе должен соответствовать запросу");
            assertEquals(loginData.getPassword(), loginResponse.getPassword(), "Password в ответе должен соответствовать запросу");
        });
    }

    @Feature("Api tests")
    @Story("reqres")
    @Tag("AutoRun")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET: Получение списка пользователей с пагинацией")
    @Test
    void retrieveUserListWithPaginationTest() {

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
            assertEquals(pageNumber, response.getPage(), "Номер страницы должен быть равен 2");
        });

        step("Проверяем количество элементов на странице", () -> {
            assertEquals(6, response.getPer_page(), "Количество элементов на странице должно быть равно 6");
        });

        step("Проверяем общее количество пользователей", () -> {
            assertTrue(response.getTotal() > 0, "Общее количество пользователей должно быть больше 0");
        });

        step("Проверяем общее количество страниц", () -> {
            assertTrue(response.getTotal_pages() > 0, "Общее количество страниц должно быть больше 0");
        });

        step("Проверяем наличие данных пользователей", () -> {
            assertNotNull(response.getData(), "Данные пользователей не должны быть null");
            assertFalse(response.getData().isEmpty(), "Данные пользователей не должны быть пустыми");
        });
        
        step("Проверяем данные первого пользователя на странице", () -> {
            UserModel firstUser = response.getData().get(0);
            assertTrue(firstUser.getId() > 0, "ID пользователя должен быть положительным");
            assertNotNull(firstUser.getEmail(), "Email пользователя не должен быть null");
            assertNotNull(firstUser.getFirst_name(), "Имя пользователя не должно быть null");
            assertNotNull(firstUser.getLast_name(), "Фамилия пользователя не должна быть null");
            assertNotNull(firstUser.getAvatar(), "Аватар пользователя не должен быть null");
        });
    }
}