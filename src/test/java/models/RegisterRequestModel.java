package models;

import lombok.Data;

@Data
public class RegisterRequestModel extends AuthRequestModel {
    
    
    public RegisterRequestModel(String email, String password) {
        super(email, password);
    }
    
    public static RegisterRequestModel createSuccessfulRegisterData() {
        return new RegisterRequestModel("eve.holt@reqres.in", "pistol");
    }
    
}
