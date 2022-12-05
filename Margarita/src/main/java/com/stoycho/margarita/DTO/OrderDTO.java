package com.stoycho.margarita.DTO;

import com.stoycho.margarita.enums.OrderStatus;
import com.stoycho.margarita.model.Order;
import com.stoycho.margarita.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String orderNumber;
    private LocalDate date;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private List<OrderItemDTO> orderItemDTOS= new ArrayList<>();
    private Double total;
    private OrderStatus status;

    public OrderDTO(Order order) {
        this.orderId=order.getOrder_id();
        this.orderNumber = order.getOrder_number();
        this.date = order.getDate();
        this.customerName = order.getUser().getName();
        this.customerPhone = order.getUser().getPhone();
        this.customerAddress = order.getUser().getStreet()+" "+order.getUser().getStreetNumber();
        for (OrderItem item:order.getItems()) {
            this.orderItemDTOS.add(new OrderItemDTO(item));
        }
        this.total = order.getTotal();
        this.status = order.getStatus();
    }

}
