package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponseModel {
    private String id;
    private String email;
    private String password;
    private String createdAt;
    private String error;
    
    // Конструктор по умолчанию
    public RegisterResponseModel() {}
    
    // Конструктор для успешной регистрации
    public RegisterResponseModel(String id, String email, String password, String createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }
    
    // Конструктор для ошибки
    public RegisterResponseModel(String error) {
        this.error = error;
    }
}
