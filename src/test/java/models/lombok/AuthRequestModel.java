package models.lombok;

import lombok.Data;

@Data
public abstract class AuthRequestModel {
    protected String email;
    protected String password;
    
    // Конструктор по умолчанию
    public AuthRequestModel() {}
    
    // Конструктор с параметрами
    public AuthRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
