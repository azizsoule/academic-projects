package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "payment_detail_id_payment_detail", nullable = false)
    private PaymentDetail paymentDetail;

    @ManyToOne
    @JoinColumn(name = "code_order_state")
    private OrderState orderState;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    private Date createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

}