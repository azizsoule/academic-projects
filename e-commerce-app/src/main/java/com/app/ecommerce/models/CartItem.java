package com.app.ecommerce.models;

import com.app.ecommerce.models.supers.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "cart_item")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class CartItem extends Item {

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}