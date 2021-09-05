package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "payment_detail")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class PaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaymentDetail;

    private String reference;

    private float amount;

    private String status;

    private Date createdAt;

    @OneToOne(optional = false, mappedBy = "paymentDetail")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "payment_method_id_payment_method")
    private PaymentMethod paymentMethod;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

}