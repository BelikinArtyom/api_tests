package models;

import lombok.Data;

@Data
public class RequestBodyModel {
    private String name;
    private String job;
    
    public static RequestBodyModel create(String name, String job) {
        RequestBodyModel model = new RequestBodyModel();
        model.setName(name);
        model.setJob(job);
        return model;
    }
}
