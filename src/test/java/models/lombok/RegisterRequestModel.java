package models.lombok;

import lombok.Data;

@Data
public class RegisterRequestModel {
    private String email;
    private String password;
    
    // Конструктор по умолчанию
    public RegisterRequestModel() {}
    
    // Конструктор с параметрами
    public RegisterRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    // Статический метод для создания данных для успешной регистрации
    public static RegisterRequestModel createSuccessfulRegisterData() {
        return new RegisterRequestModel("eve.holt@reqres.in", "pistol");
    }
    
    // Статический метод для создания данных для неуспешной регистрации
    public static RegisterRequestModel createFailedRegisterData() {
        return new RegisterRequestModel("sydney@fife", "");
    }
}
