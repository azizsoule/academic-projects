package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CustomerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctype_id")
    private Long idCustomerType;

    @Column(length = 30)
    private String label;

    private int startValue;

    private int endValue;

    @OneToMany(mappedBy = "customerType")
    private Set<Customer> customers;

}
