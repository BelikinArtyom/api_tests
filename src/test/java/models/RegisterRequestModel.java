package models;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class RegisterRequestModel {
    private String username;
    private String email;
    private String password;
    
    public RegisterRequestModel() {}
    
    public RegisterRequestModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public static RegisterRequestModel createSuccessfulRegisterData() {
        Faker faker = new Faker();
        return new RegisterRequestModel(
            faker.name().username(),
            faker.internet().emailAddress(),
            faker.internet().password(8, 16)
        );
    }
}
