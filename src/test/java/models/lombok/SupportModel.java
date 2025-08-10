package models.lombok;

import lombok.Data;

@Data
public class SupportModel {
    private String url;
    private String text;
    
    // Конструктор по умолчанию
    public SupportModel() {}
    
    // Конструктор с параметрами
    public SupportModel(String url, String text) {
        this.url = url;
        this.text = text;
    }
}
