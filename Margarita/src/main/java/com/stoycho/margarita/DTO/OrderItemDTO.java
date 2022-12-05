package com.stoycho.margarita.DTO;

import com.stoycho.margarita.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDTO {
    private Long productId;
    private int quantity;
    public OrderItemDTO(OrderItem orderItem){
        this.productId= orderItem.getItem_id();
        this.quantity= orderItem.getQuantity();
    }
}
