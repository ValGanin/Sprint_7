// Тест: проверка получения списка всех заказов
package tests;

import org.junit.Test;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import static steps.OrderSteps.*;
import static org.hamcrest.Matchers.*;

public class OrderListTest {

    @Test
    @Description("Получение списка заказов")
    public void testOrderList() {
        Response r = list()
                .then()
                .extract()
                .response();
        r.then().statusCode(200).body("orders", notNullValue());
    }
}