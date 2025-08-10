package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;
import models.lombok.UserModel;
import models.lombok.SupportModel;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListResponseModel {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<UserModel> data;
    private SupportModel support;
    
    // Конструктор по умолчанию
    public UserListResponseModel() {}
}


