package models;

import lombok.Data;

@Data
public class PatchResponseModel extends ApiResponseModel {
    private String name;
    private String job;
}
