package com.app.ecommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = "article")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    @Column(length = 50)
    private String label;

    private String description;

    @Column(length = 20)
    private String sku;

    private float price;

    private int commentCount;

    private int ratingSum;

    private String image;

    private String brand;

    @OneToMany(mappedBy = "article")
    private Set<Image> images;

    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;

    @OneToOne(orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id_inventory")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "sub_category_id_sub_category")
    private SubCategory subCategory;

    @ManyToMany(mappedBy = "articles")
    private Set<Discount> discounts;

    @OneToOne(mappedBy = "article")
    private CartItem cartItem;

    @OneToOne(mappedBy = "article")
    private OrderItem orderItem;

    @OneToOne(mappedBy = "article")
    private WishItem wishItem;

}