// содержит базовый URI и пути к эндпоинтам API
package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class Config {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    public static final String COURIER_PATH = "/api/v1/courier";
    public static final String COURIER_LOGIN_PATH = "/api/v1/courier/login";
    public static final String ORDERS_PATH = "/api/v1/orders";

    // Читаем из mvn: -Dallure.logging=true
    private static final boolean ALLURE_LOG =
            Boolean.parseBoolean(System.getProperty("allure.logging", "false"));

    static {
        RestAssured.baseURI = BASE_URL;
        if (Boolean.getBoolean("allure.logging")) {
            RestAssured.filters(new AllureRestAssured());
        }
    }
}