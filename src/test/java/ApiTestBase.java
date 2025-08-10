import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ApiTestBase {
    
    protected static final String BASE_URI = System.getProperty("base.uri", "https://reqres.in");
    protected static final String BASE_PATH = System.getProperty("base.path", "/api");
    
    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }
}
