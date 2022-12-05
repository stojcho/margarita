package com.stoycho.margarita.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "id_AUTO")
    @SequenceGenerator(name = "id_AUTO", sequenceName = "ORACLE_DB_AUTO_ID")
    private Long id;

    @Column(name="name")
    private String name; // full name, e.g., "Joe Smith"

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="street")
    private String street;

    @Column(name="street_number")
    private String streetNumber;

    @Column(name="zip_code")
    private String zipCode;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Role> roles = new ArrayList<>();

    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_card_id")
    @ToString.Exclude
    private ShoppingCard shoppingCard;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}