package models.lombok;

import lombok.Data;

import java.time.LocalDateTime;

    @Data
    public class RequestBodyModel {
        String name;
        String job;
        String createdAt;
        String updatedAt;
        int id;

        // Конструктор по умолчанию
        public RequestBodyModel() {}

        // Конструктор с параметрами для удобства
        public RequestBodyModel(String name, String job) {
            this.name = name;
            this.job = job;
        }

        // Статический метод для создания данных для PATCH запроса
        public static RequestBodyModel createPatchData() {
            RequestBodyModel model = new RequestBodyModel();
            model.setName("morpheus");
            model.setJob("zion resident");
            return model;
        }

        public static RequestBodyModel createPatchSingle() {
            RequestBodyModel model = new RequestBodyModel();
            model.setName("");
            model.setJob("zion resident");
            return model;
        }

        // Статический метод для создания данных для POST запроса
        public static RequestBodyModel createPostData() {
            RequestBodyModel model = new RequestBodyModel();
            model.setName("morpheus");
            model.setJob("leader");
            return model;
        }


        public static RequestBodyModel createCustomData(String name, String job) {
            return new RequestBodyModel(name, job);
        }


        public static String getCurrentYear() {
            return String.valueOf(LocalDateTime.now().getYear());
        }

        public static String getCurrentMonth() {
            return String.format("%02d", LocalDateTime.now().getMonthValue());
        }
    }
