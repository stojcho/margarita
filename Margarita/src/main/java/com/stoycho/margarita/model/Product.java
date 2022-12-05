package com.stoycho.margarita.model;

import com.stoycho.margarita.enums.ProductType;
import com.stoycho.margarita.exception.BadRequestException;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="price",nullable = false)
    private double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType category;

    @Column(name="imageURL",nullable = false)
    private String imageURL;

    public Product(String name, String description, Double price, String category) {
        this.name=name;
        this.description=description;
        this.price=price;
        switch (category) {
            case "BASIC":
            case "basic":
                this.category = ProductType.BASIC;
                break;
            case "WEDDING":
            case "wedding":
                this.category = ProductType.WEDDING;
                break;
            case "SPECIAL":
            case "special":
                this.category = ProductType.SPECIAL;
                break;
            case "BIRTHDAY":
            case "birthday":
                this.category = ProductType.BIRTHDAY;
                break;
            default:
                throw new BadRequestException("There no category matching.");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}