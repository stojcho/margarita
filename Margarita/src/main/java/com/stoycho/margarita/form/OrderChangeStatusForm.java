package com.stoycho.margarita.form;

import com.stoycho.margarita.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderChangeStatusForm {
    private Long orderId;
    private OrderStatus status;
}
