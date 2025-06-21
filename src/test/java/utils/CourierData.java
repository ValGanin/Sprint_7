// генерация тестовых данных курьера
package utils;

import java.util.UUID;

public class CourierData {
    private String login, password, firstName;
    private CourierData() {
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public static CourierData random() {
        CourierData d = new CourierData();
        d.login = "user_" + UUID.randomUUID().toString().substring(0,8);
        d.password = "pass_" + UUID.randomUUID().toString().substring(0,8);
        d.firstName = "name_" + UUID.randomUUID().toString().substring(0,5);
        return d;
    }
}