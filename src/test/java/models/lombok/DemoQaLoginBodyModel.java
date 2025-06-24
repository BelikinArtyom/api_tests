package models.lombok;


import lombok.Data;

@Data
public class DemoQaLoginBodyModel {

    String userName;
    String password;

    public static DemoQaLoginBodyModel createTestData() {
        DemoQaLoginBodyModel model = new DemoQaLoginBodyModel();
        model.setUserName("Mestray");
        model.setPassword("BEPcAxf5DsKk~@_");
        return model;
    }


}
