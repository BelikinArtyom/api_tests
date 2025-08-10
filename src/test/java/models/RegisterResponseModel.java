package models;

import lombok.Data;

@Data
public class RegisterResponseModel extends ApiResponseModel {
    private String email;
    private String password;
    private String username;
}
