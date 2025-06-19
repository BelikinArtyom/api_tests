package models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
 public class DataModel {
    private int id;
    private String name;
    private int year;
    private String color;

    @JsonProperty("pantone_value")
    private String pantoneValue;
}
