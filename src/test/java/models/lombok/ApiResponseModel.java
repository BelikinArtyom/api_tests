package models.lombok;

import lombok.Data;

@Data
public abstract class ApiResponseModel {
    protected String id;
    protected String createdAt;
    protected String updatedAt;
    protected String error;
}
