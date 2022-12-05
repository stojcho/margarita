package com.stoycho.margarita.model;

import com.stoycho.margarita.generator.RandomStringGenerator;
import com.stoycho.margarita.enums.OrderStatus;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="\"Order\"")
@Entity
public class Order {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "ORDER_ID_AUTO")
    private Long order_id;

    @Column(unique = true,nullable = false, length=200)
    private String order_number;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "order_item_id", referencedColumnName = "item_id")
    )
    private List<OrderItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @PrePersist
    protected void onCreate() {
        setOrder_number(RandomStringGenerator.getAlphaNumericString(10));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return Objects.equals(order_id, order.order_id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}