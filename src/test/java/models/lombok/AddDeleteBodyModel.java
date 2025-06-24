package models.lombok;

import java.util.List;
import java.util.Map;
import lombok.Data;

import static java.lang.String.format;

@Data
public class AddDeleteBodyModel {

    String userId;
    String Isbn;



    public static AddDeleteBodyModel addBook() {
        AddDeleteBodyModel model = new AddDeleteBodyModel();
        model.setUserId("0614ae60-ffda-4fe0-88a2-092b2da53b15");
        model.setIsbn("9781449337711");
        return model;
    }


    public static AddDeleteBodyModel DeleteBookData() {
        AddDeleteBodyModel model = new AddDeleteBodyModel();
        model.setUserId("0614ae60-ffda-4fe0-88a2-092b2da53b15");
        model.setIsbn("9781449337711");
        return model;
    }
}
