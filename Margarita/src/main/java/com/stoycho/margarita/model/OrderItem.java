package com.stoycho.margarita.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {

    @Id
    @Column(name = "item_id", unique = true, nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "ITEM_ID_AUTO")
    private Long item_id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public OrderItem(@NotNull OrderItem orderItem) {
        // Copy all the fields of OrderItem.
        this.item_id=orderItem.getItem_id();
        this.product=orderItem.getProduct();
        this.quantity=orderItem.getQuantity();
    }
}
