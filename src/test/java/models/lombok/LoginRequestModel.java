package models.lombok;

import lombok.Data;

@Data
public class LoginRequestModel extends AuthRequestModel {
    private String username;
    
    // Конструктор по умолчанию
    public LoginRequestModel() {}
    
    // Конструктор с параметрами
    public LoginRequestModel(String email, String password, String username) {
        super(email, password);
        this.username = username;
    }
    
    // Статический метод для создания данных для успешной авторизации
    public static LoginRequestModel createSuccessfulLoginData() {
        return new LoginRequestModel("eve.holt@reqres.in", "cityslicka", "eve.holt");
    }
    
    // Статический метод для создания данных для неуспешной авторизации
    public static LoginRequestModel createFailedLoginData() {
        return new LoginRequestModel("peter@klaven", "cityslicka", "peter");
    }
}
