package com.app.ecommerce.models;

import com.app.ecommerce.models.supers.Item;
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

    @OneToOne
    @JoinColumn(name = "article_id_article")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}