package com.app.ecommerce.models;

import com.app.ecommerce.models.supers.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "wish_item")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class WishItem extends Item {

    @OneToOne
    @JoinColumn(name = "article_id_article")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;

}