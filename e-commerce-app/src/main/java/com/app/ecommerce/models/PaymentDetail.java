package com.app.ecommerce.models;

import com.app.ecommerce.utils.Generator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-mm-dd", fallbackPatterns = "yyyy/mm/dd")
    private Date createdAt;

    @OneToOne(mappedBy = "paymentDetail")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_payment_method")
    private PaymentMethod paymentMethod;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        reference = "REF"+Generator.generate(5);
    }

}