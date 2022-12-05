package com.stoycho.margarita.DTO;

import com.stoycho.margarita.model.OrderItem;
import lombok.Data;

@Data
public class CardItemDTO {
    private Long orderItemId;
    private String name;
    private Double price;
    private String imageURL;
    private int quantity;
    public CardItemDTO(OrderItem orderItem) {
        this.orderItemId=orderItem.getItem_id();
        this.name = orderItem.getProduct().getName();
        this.price = orderItem.getProduct().getPrice();
        this.imageURL = orderItem.getProduct().getImageURL();
        this.quantity = orderItem.getQuantity();
    }
}
