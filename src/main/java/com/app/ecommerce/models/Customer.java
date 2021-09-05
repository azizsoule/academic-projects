package com.app.ecommerce.models;

import com.app.ecommerce.models.supers.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Customer extends Person {

    @OneToMany(mappedBy = "customer")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private Set<CustomerPaymentMethod> customerPaymentMethods;

    @OneToMany(mappedBy = "customer")
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy = "customer")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private Set<Order> orders;

    @ManyToMany(mappedBy = "customers")
    private Set<Discount> discounts;

    @ManyToOne
    @JoinColumn(name = "customer_type_id_customer_type")
    private CustomerType customerType;

    @ManyToOne
    @JoinColumn(name = "sex_id_sex")
    private Sex sex;

}
