package models.lombok;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
public class AddDeleteBodyModel {

    String userId;
    String isbn;

    @Data
    public static class IsbnModel {
        String isbn;
    }

    List<IsbnModel> collectionOfIsbns;


    public static AddDeleteBodyModel DeleteBookData() {
        AddDeleteBodyModel model = new AddDeleteBodyModel();
        model.setUserId("0614ae60-ffda-4fe0-88a2-092b2da53b15");
        model.setIsbn("9781449337711");
        return model;
    }


    public static AddDeleteBodyModel addBook() {
        AddDeleteBodyModel model = new AddDeleteBodyModel();
        model.setUserId("0614ae60-ffda-4fe0-88a2-092b2da53b15");

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn("9781449337711");

        List<IsbnModel> collectionOfIsbns = new ArrayList<>();
        collectionOfIsbns.add(isbnModel);
        model.setCollectionOfIsbns(collectionOfIsbns);

        return model;
    }


}
