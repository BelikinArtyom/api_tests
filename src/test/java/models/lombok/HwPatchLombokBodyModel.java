package models.lombok;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HwPatchLombokBodyModel {
    String name;
    String job;

    public static HwPatchLombokBodyModel createTestData() {
        HwPatchLombokBodyModel model = new HwPatchLombokBodyModel();
        model.setName("morpheus");
        model.setJob("zion resident");
        return model;
    }

    public static String getCurrentYear() {
        return String.valueOf(LocalDateTime.now().getYear());
    }

    public static String getCurrentMonth() {
        return String.format("%02d", LocalDateTime.now().getMonthValue());
    }
}