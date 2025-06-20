package models.lombok;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HwPatchSingleLombokBodyModel {
    String name;
    String job;

    public static HwPatchSingleLombokBodyModel createTestData() {
        HwPatchSingleLombokBodyModel model = new HwPatchSingleLombokBodyModel();
        model.setName("");
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