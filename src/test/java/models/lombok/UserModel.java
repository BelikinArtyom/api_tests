package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
    
    // Конструктор по умолчанию
    public UserModel() {}
}
