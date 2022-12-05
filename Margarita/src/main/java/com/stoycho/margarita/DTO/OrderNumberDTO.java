package com.stoycho.margarita.DTO;

import com.stoycho.margarita.model.Order;
import lombok.Data;

@Data
public class OrderNumberDTO {
    public OrderNumberDTO(Order order) {
        this.orderNumber = order.getOrder_number();
    }

    private String orderNumber;

}
