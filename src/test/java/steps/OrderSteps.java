// API-методы для операций с заказами (create, list)
package steps;

import io.qameta.allure.Step;
import config.Config;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class OrderSteps {
    @Step("Создать заказ с данными {order}")
    public static Response create(Map<String,Object> order) {
        return given()
                .when()
                .baseUri(Config.BASE_URL)
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(order)
                .post(Config.ORDERS_PATH);
    }
    @Step("Получить список заказов")
    public static Response list() {
        return given()
                .when()
                .baseUri(Config.BASE_URL)
                .filter(new AllureRestAssured())
                .get(Config.ORDERS_PATH);
    }
}