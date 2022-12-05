package com.stoycho.margarita.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class ShoppingCard {

    public ShoppingCard(User user) {
        this.user=user;
    }

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "CARD_ID_AUTO")
    private Long card_id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "shopping_card_item",
            joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "card_item_id", referencedColumnName = "item_id")
    )
    private List<OrderItem> items = new ArrayList<>();
    @JsonIgnore
    @OneToOne(mappedBy = "shoppingCard")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShoppingCard that = (ShoppingCard) o;
        return Objects.equals(card_id, that.card_id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
