package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseModel {
    private String id;
    private String username;
    private String email;
    private String password;
    private String createdAt;
}
