package models;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String username;
    private String email;
    private String password;
    
    public LoginRequestModel() {}
    
    public LoginRequestModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    

}
