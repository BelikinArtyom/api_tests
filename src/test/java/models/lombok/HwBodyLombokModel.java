package models.lombok;

import lombok.Data;

import java.time.LocalDateTime;

    @Data
    public class HwBodyLombokModel {
        String name;
        String job;
        String createdAt;
        String updatedAt;
        int id;

        // Конструктор по умолчанию
        public HwBodyLombokModel() {}

        // Конструктор с параметрами для удобства
        public HwBodyLombokModel(String name, String job) {
            this.name = name;
            this.job = job;
        }

        // Статический метод для создания данных для PATCH запроса
        public static HwBodyLombokModel createPatchData() {
            HwBodyLombokModel model = new HwBodyLombokModel();
            model.setName("morpheus");
            model.setJob("zion resident");
            return model;
        }

        public static HwBodyLombokModel createPatchSingle() {
            HwBodyLombokModel model = new HwBodyLombokModel();
            model.setName("");
            model.setJob("zion resident");
            return model;
        }

        // Статический метод для создания данных для POST запроса
        public static HwBodyLombokModel createPostData() {
            HwBodyLombokModel model = new HwBodyLombokModel();
            model.setName("morpheus");
            model.setJob("leader");
            return model;
        }


        public static HwBodyLombokModel createCustomData(String name, String job) {
            return new HwBodyLombokModel(name, job);
        }


        public static String getCurrentYear() {
            return String.valueOf(LocalDateTime.now().getYear());
        }

        public static String getCurrentMonth() {
            return String.format("%02d", LocalDateTime.now().getMonthValue());
        }
    }
