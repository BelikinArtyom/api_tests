package models.pojo;

public class LoginBodyModel {

//    String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

    public String getApiKey() {
        return ApiKey;
    }

    public void setApiKey(String token) {
        this.ApiKey = token;
    }

    String ApiKey;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;

}