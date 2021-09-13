package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "customer_pm")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class CustomerPaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomerPayment;

    private String ccNumber;

    private Date ccExpirationDate;

    private String cvv;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_payment_type")
    private PaymentMethod paymentMethod;

}