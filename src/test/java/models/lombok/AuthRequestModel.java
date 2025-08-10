package models.lombok;

import lombok.Data;

@Data
public abstract class AuthRequestModel {
    protected String email;
    protected String password;
    
    public AuthRequestModel() {}
    
    public AuthRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
