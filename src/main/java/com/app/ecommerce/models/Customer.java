package com.app.ecommerce.models;

import com.app.ecommerce.models.supers.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Customer extends Person {

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<CustomerPaymentMethod> customerPaymentMethods = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<CartItem> cartItems = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    @OrderBy("createdAt DESC")
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private Set<WishItem> wishItems = new HashSet<>();

    @ManyToMany(mappedBy = "customers")
    private Set<Discount> discounts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "customer_type_id")
    private CustomerType customerType;

    @ManyToOne
    @JoinColumn(name = "sex_id_sex")
    private Sex sex;

}
