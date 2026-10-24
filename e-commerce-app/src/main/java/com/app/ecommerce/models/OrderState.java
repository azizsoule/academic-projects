package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "order_state")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderState {

    @Id
    @Column(name = "code_order_state", nullable = false, length = 30)
    private String codeOrderState;

    @Column(length = 30)
    private String label;

    @OneToMany(mappedBy = "orderState")
    private Set<Order> orders;

    public OrderState(String code,String label){
        codeOrderState=code;
        this.label=label;
    }

}