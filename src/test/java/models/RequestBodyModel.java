package models;

import lombok.Data;

@Data
public class RequestBodyModel {
    private String name;
    private String job;
    private String createdAt;
    private String updatedAt;
    private int id;
    
    public RequestBodyModel() {}
    
    public RequestBodyModel(String name, String job) {
        this.name = name;
        this.job = job;
    }
    
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
    
    public static RequestBodyModel createPostData() {
        RequestBodyModel model = new RequestBodyModel();
        model.setName("morpheus");
        model.setJob("leader");
        return model;
    }


}
