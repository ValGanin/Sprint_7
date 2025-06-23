// генерация тестовых данных курьера
package utils;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CourierData {
    private String login, password, firstName;
    public static CourierData random() {
        return new CourierData(
                "user_" + UUID.randomUUID().toString().substring(0, 8),
                "pass_" + UUID.randomUUID().toString().substring(0, 8),
                "name_" + UUID.randomUUID().toString().substring(0, 5)
        );
    }
}