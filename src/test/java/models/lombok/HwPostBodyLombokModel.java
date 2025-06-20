package models.lombok;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HwPostBodyLombokModel {

    String name;
    String job;
    String createdAt;
    int id;


    public static HwPostBodyLombokModel createTestData() {
        HwPostBodyLombokModel model = new HwPostBodyLombokModel();
        model.setName("morpheus");
        model.setJob("leader");
        return model;
    }

    public static String getCurrentYear() {
        return String.valueOf(LocalDateTime.now().getYear());
    }

    public static String getCurrentMonth() {
        return String.format("%02d", LocalDateTime.now().getMonthValue());
    }
}
