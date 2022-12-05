package com.stoycho.margarita.DTO;

import com.stoycho.margarita.model.OrderItem;
import com.stoycho.margarita.model.ShoppingCard;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class ShoppingCardDTO {
    private List<CardItemDTO> items=new ArrayList<>();
    private double total=0;
    private int totalQuantity=0;

    public ShoppingCardDTO(ShoppingCard shoppingCard) {
        for (OrderItem orderItem : shoppingCard.getItems()) {
            this.items.add(new CardItemDTO(orderItem));
            this.total+=orderItem.getProduct().getPrice()*orderItem.getQuantity();
            this.totalQuantity+= orderItem.getQuantity();
        }
    }
    public ShoppingCardDTO(ShoppingCard shoppingCard,boolean isItems,boolean isTotal,boolean isTotalQuantity) {
        if(isItems||isTotal||isTotalQuantity){
            for (OrderItem orderItem : shoppingCard.getItems()) {
                if(isItems){
                    this.items.add(new CardItemDTO(orderItem));
                }
                if(isTotal){
                    this.total+=orderItem.getProduct().getPrice()*orderItem.getQuantity();
                }
                if(isTotalQuantity){
                    this.totalQuantity+= orderItem.getQuantity();
                }
            }
        }
    }
}
