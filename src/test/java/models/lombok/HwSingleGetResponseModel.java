package models.lombok;

import lombok.Data;

@Data
public class HwSingleGetResponseModel {
    private DataModel data;
    private SupportModel support;
}

@Data
 class SupportModel {
    private String url;
    private String text;
}

