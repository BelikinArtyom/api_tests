package models.lombok;

import lombok.Data;

@Data
public class LoginRequestModel extends AuthRequestModel {
    private String username;
    
    public LoginRequestModel(String email, String password, String username) {
        super(email, password);
        this.username = username;
    }
    
    public static LoginRequestModel createSuccessfulLoginData() {
        return new LoginRequestModel("eve.holt@reqres.in", "cityslicka", "eve.holt");
    }
}
