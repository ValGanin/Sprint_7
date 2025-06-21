package tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utils.OrderData;
import io.restassured.response.Response;
import java.util.*;
import io.qameta.allure.Description;
import static org.junit.runners.Parameterized.*;
import static steps.OrderSteps.*;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private final OrderData orderData;
    public OrderCreateTest(OrderData od) {
        this.orderData = od;
    }
    @Parameters(name="Colors: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {OrderData.builder().withColor(List.of("BLACK"))},
                {OrderData.builder().withColor(List.of("GREY"))},
                {OrderData.builder().withColor(List.of("BLACK","GREY"))},
                {OrderData.builder().withColor(Collections.emptyList())}
        });
    }
    @Test
    @Description("Создание заказа с параметризацией цвета")
    public void testOrderCreate() {
        Response r = create(orderData.toMap())
                .then()
                .extract()
                .response();
        r.then().statusCode(201).body("track",notNullValue());
    }
}
