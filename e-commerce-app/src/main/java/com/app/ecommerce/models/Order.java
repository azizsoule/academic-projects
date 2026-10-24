package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "order_detail")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    private float total;

    @ManyToOne
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_payment_detail")
    private PaymentDetail paymentDetail;

    @ManyToOne
    @JoinColumn(name = "code_order_state")
    private OrderState orderState;

    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    private Date createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

}