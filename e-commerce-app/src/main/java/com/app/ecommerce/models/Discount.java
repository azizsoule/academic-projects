package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "discount")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiscount;

    @Column(unique = true)
    private String promoCode;

    @Column(length = 30)
    private String label;

    private String description;

    private int discountPercent;

    private float discountAmount;

    private boolean percentage;

    private boolean enabled = true;

    @DateTimeFormat(pattern = "yyyy-mm-dd", fallbackPatterns = "yyyy/mm/dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-mm-dd", fallbackPatterns = "yyyy/mm/dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd", fallbackPatterns = "yyyy/mm/dd")
    private Date endDate;

    @ManyToMany
    private Set<Article> articles;

    @ManyToMany
    @JoinTable(name = "discount_customer",
            joinColumns = @JoinColumn(name = "discount_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private Set<Customer> customers;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

}