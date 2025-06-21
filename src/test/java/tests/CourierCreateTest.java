package tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.*;
import org.junit.*;
import utils.CourierData;
import static steps.CourierSteps.*;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.Description;
import java.util.Map;

public class CourierCreateTest {
    private CourierData courier;
    private Integer courierId;
    @Before
    public void setUp() {
        courier = CourierData.random();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    }
    @Test
    @Description("Создание курьера с обязательными полями")
    public void testCreate() {
        Response r = create(courier)
                .then()
                .extract()
                .response();
        r.then().statusCode(201).body("ok",equalTo(true));
        courierId = login(courier);
    }
    @Test
    @Description("Нельзя создать двух одинаковых курьеров")
    public void testDuplicate() {
        create(courier)
                .then()
                .statusCode(201)
                .extract()
                .response();
        courierId = login(courier);
        create(courier).then().statusCode(409).body("message",containsString("Этот логин уже используется. Попробуйте другой.")).
                extract().response();
    }
    @Test
    @Description("Проверка обязательных полей при создании курьера")
    public void testMissingFields() {
        Map<String, String> body = Map.of(
                "login", courier.getLogin()
        );
        Response r = create(body);
        r.then()
                .statusCode(400)
                .body("message", containsString("Недостаточно данных"))
                .extract()
                .response();
    }
    @After
    public void tearDown() {
        if (courierId!=null) delete(courierId);
    }
}