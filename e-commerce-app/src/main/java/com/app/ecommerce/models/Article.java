package com.app.ecommerce.models;

import com.app.ecommerce.utils.Constants;
import com.app.ecommerce.utils.Generator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
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

    @Type(type = "text")
    private String description;

    @Column(length = 20, unique = true)
    private String sku;

    private float price;

    private float ratingSum = 0;

    private String image;

    private String brand;

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @OrderBy("createdAt DESC")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_inventory")
    @JsonIgnore
    private Inventory inventory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sub_category")
    @JsonIgnore
    private SubCategory subCategory;

    @ManyToMany(mappedBy = "articles")
    @JsonIgnore
    private Set<Discount> discounts = new HashSet<>();

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private Set<OrderItem> orderItems;

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private Set<WishItem> wishItems;

    @PrePersist
    public void prePersist() {
        sku = "SKU"+ Generator.generate(5);
    }

    @Transient
    public String getImagePath() {
        return Constants.MEDIA_SERVER_ENDPOINT + "/articles/" + sku + "/" + image;
    }

}