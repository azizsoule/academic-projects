package com.app.ecommerce.models;

import com.app.ecommerce.models.supers.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "order_item")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderItem extends Item {

    @OneToOne
    @JoinColumn(name = "article_id_article")
    private Article article;

    @ManyToOne
    private Order order;

}