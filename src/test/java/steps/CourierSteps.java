// API-методы для операций с курьером (create, login, delete)
package steps;

import io.qameta.allure.Step;
import config.Config;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.CourierData;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class CourierSteps {
    @Step("Создать курьера")
    public static Response create(CourierData c) {
        return given()
                .baseUri(Config.BASE_URL)
                .filter(new AllureRestAssured())
                .when()
                .contentType(ContentType.JSON).body(c)
                .post(Config.COURIER_PATH);
    }
    @Step("Попытка авторизации с неполными/неверными данными")
    public static Response login(Map<String, String> body) {
        return given()
                .baseUri(Config.BASE_URL)
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(body)
                .when().post(Config.COURIER_LOGIN_PATH);
    }
    @Step("Авторизовать курьера и получить ID")
    public static Integer login(CourierData c) {
        Response r = given()
                .baseUri(Config.BASE_URL)
                .filter(new AllureRestAssured())
                .when()
                .contentType(ContentType.JSON)
                .body(Map.of("login",c.getLogin(),"password",c.getPassword()))
                .post(Config.COURIER_LOGIN_PATH);
        return r.then().statusCode(200).extract().path("id");
    }
    @Step("Удалить курьера с ID {id}")
    public static void delete(Integer id) {
        given()
                .baseUri(Config.BASE_URL)
                .filter(new AllureRestAssured())
                .when()
                .delete(Config.COURIER_PATH+"/"+id)
                .then().statusCode(anyOf(is(200),is(404)));
    }

    @Step("Попытка создать курьера с неполными данными")
    public static Response create(Map<String,String> body) {
        return given()
                .baseUri(Config.BASE_URL)
                .filter(new AllureRestAssured())
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(Config.COURIER_PATH);
    }
}
