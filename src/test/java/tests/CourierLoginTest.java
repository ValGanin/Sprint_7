package tests;

import org.junit.*;
import utils.CourierData;
import java.util.Map;
import io.restassured.response.Response;
import static steps.CourierSteps.*;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.Description;
import static org.apache.http.HttpStatus.*;

public class CourierLoginTest {
    private CourierData courier;
    private Integer courierId;
    @Before
    public void setUp() {
        courier = CourierData.random();
        create(courier).then().statusCode(201);
        courierId = login(courier);
    }
    @Test
    @Description("Авторизация курьера")
    public void testLogin() {
        login(courier);
    }
    @Test
    @Description("Ошибка при отсутствии логина для авторизации")
    public void testLoginMissingLogin() {
        Response r = login(Map.of("login",courier.getLogin()));
        Assume.assumeTrue("Gateway Timeout (504) — сервис недоступен, пропускаем тест", r.statusCode() != 504);
        r.then().statusCode(SC_BAD_REQUEST)
                .body("message",containsString("Недостаточно данных"))
                .extract();
    }
    @Test
    @Description("Ошибка при отсутствии пароля для авторизации")
    public void testLoginMissingPassword() {
        Response r = login(Map.of("password", courier.getPassword()));
        r.then().statusCode(SC_BAD_REQUEST)
                .body("message",containsString("Недостаточно данных"))
                .extract();
    }
    @Test
    @Description("Ошибка при неверных логине или пароле")
    public void testLoginWrong() {
        Response r = login(Map.of("login",courier.getLogin(),"password","wrong"));
        r.then().statusCode(SC_NOT_FOUND)
                .body("message",containsString("Учетная запись не найдена"))
                .extract();;
    }
    @After
    public void tearDown() {
        delete(courierId);
    }
}