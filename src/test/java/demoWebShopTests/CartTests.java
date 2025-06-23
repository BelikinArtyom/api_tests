package demoWebShopTests;

import helpers.TestBase;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CartTests extends TestBase {

    String login = "qa@qa.guru",
            password = "qa@qa.guru1";

        @Test
        void addToCartTest() {

            String authCookieKey = "NOPCOMMERCE.AUTH";
            String authCookieValue = given()
                    .headers("Content-type", "application/x-www-form-urlencoded")
                    .formParam("email", login)
                    .formParam("password", password)
                    .when()
                    .post("https://demowebshop.tricentis.com/login")
                    .then()
                    .log().all()
                    .statusCode(302)
                    .extract()
                    .cookie(authCookieKey);

            String data = "product_attribute_72_5_18=52" +
                    "&product_attribute_72_6_19=54" +
                    "&product_attribute_72_3_20=58" +
                    "&addtocart_72.EnteredQuantity=2";

            given()
                    .headers("Content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .cookie(authCookieKey, authCookieValue)
                    .body(data)
                    .when()
                    .post("https://demowebshop.tricentis.com/addproducttocart/details/72/1")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
        }

    @Test
    void addToCartAnonymousTest() {

        String data = "product_attribute_72_5_18=52" +
                "&product_attribute_72_6_19=54" +
                "&product_attribute_72_3_20=58" +
                "&addtocart_72.EnteredQuantity=2";

        given()
                .headers("Content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                .body(data)
                .when()
                .post("https://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }


}
