package models.lombok;

import lombok.Data;

@Data
public class PatchResponseModel extends ApiResponseModel {

    String name;

    String job;
}
