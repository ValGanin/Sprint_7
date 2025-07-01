// генерация данных заказа с настройкой цвета
package utils;

import java.time.LocalDate;
import java.util.*;
public class OrderData {
    private String firstName, lastName, address, phone, deliveryDate, comment;
    private int metroStation, rentTime;
    private List<String> color;
    private OrderData() {}
    public Map<String,Object> toMap() {
        Map<String,Object> m = new HashMap<>();
        m.put("firstName", firstName);
        m.put("lastName", lastName);
        m.put("address", address);
        m.put("metroStation", metroStation);
        m.put("phone", phone);
        m.put("rentTime", rentTime);
        m.put("deliveryDate", deliveryDate);
        m.put("comment", comment);
        if (color != null && !color.isEmpty())
            m.put("color", color);
        return m;
    }
    public static OrderData builder() {
        OrderData o = new OrderData();
        o.firstName = "Иван";
        o.lastName = "Петров";
        o.address = "Москва, Кутузовская 42";
        o.metroStation = 1;
        o.phone = "+7 999 999 99 99";
        o.rentTime = 5;
        o.deliveryDate = LocalDate.now().toString();
        o.comment = "Связь через телеграм";
        o.color = Collections.emptyList();
        return o;
    }
    public OrderData withColor(List<String> c) {
        this.color = c; return this;
    }
}