package models.lombok;

import lombok.Data;

import java.time.LocalDateTime;

    @Data
    public class HwBodyLombokModel {
        String name;
        String job;
        String createdAt;
        int id;


    public static HwPatchLombokBodyModel createPatchData() {
        HwPatchLombokBodyModel model = new HwPatchLombokBodyModel();
        model.setName("morpheus");
        model.setJob("zion resident");
        return model;
    }

        public static HwPostBodyLombokModel createPostData() {
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

        public static HwPatchSingleLombokBodyModel createTestData() {
            HwPatchSingleLombokBodyModel model = new HwPatchSingleLombokBodyModel();
            model.setName("");
            model.setJob("zion resident");
            return model;
        }


}
