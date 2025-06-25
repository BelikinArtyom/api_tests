package models.lombok;

import lombok.Data;


@Data
public class DemoQaBodyModel {

    String userName;
    String password;

    public static DemoQaBodyModel createTestData() {
        DemoQaBodyModel model = new DemoQaBodyModel();
        model.setUserName("Mestray");
        model.setPassword("BEPcAxf5DsKk~@_");
        return model;
    }
}
