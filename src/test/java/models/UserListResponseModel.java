package models;

import lombok.Data;
import java.util.List;

@Data
public class UserListResponseModel {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<UserModel> data;
    private SupportModel support;
}
