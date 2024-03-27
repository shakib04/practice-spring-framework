package learning.practice.pojo;

import learning.practice.enums.Order;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Sort {

    public String fieldName;

    public Order order = Order.asc;
}
